package io.github.sefiraat.emctech.emc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.StonecuttingRecipe;

import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.managers.ConfigManager;
import io.github.sefiraat.emctech.utils.EmcUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

public final class EmcCalculator {

    private static final double MULTIPLIER = 1.1;

    private static final Map<String, Double> VANILLA_EMC_VALUES = new HashMap<>();
    private static final Map<String, Double> SLIMEFUN_EMC_VALUES = new HashMap<>();
    private static final Map<String, Double> VANILLA_EMC_VALUES_MULTIPLIED = new HashMap<>();
    private static final Map<String, Double> SLIMEFUN_EMC_VALUES_MULTIPLIED = new HashMap<>();
    private static Map<String, Double> vanillaEmcValuesFiltered;
    private static Map<String, Double> slimefunEmcValuesFiltered;
    private static Map<String, Double> vanillaEmcValuesMultipliedFiltered;
    private static Map<String, Double> slimefunEmcValuesMultipliedFiltered;

    private EmcCalculator() {
        throw new IllegalStateException("Utility class");
    }

    public static void setup() {
        addBaseValues();
        setupVanilla();
        vanillaEmcValuesFiltered = cleanMap(new HashMap<>(VANILLA_EMC_VALUES));
        vanillaEmcValuesMultipliedFiltered = cleanMap(new HashMap<>(VANILLA_EMC_VALUES_MULTIPLIED));
        setupSlimefun();
        slimefunEmcValuesFiltered = cleanMap(new HashMap<>(SLIMEFUN_EMC_VALUES));
        slimefunEmcValuesMultipliedFiltered = cleanMap(new HashMap<>(SLIMEFUN_EMC_VALUES_MULTIPLIED));
    }

    private static void addBaseValues() {
        VANILLA_EMC_VALUES.putAll(ConfigManager.getInstance().getEmcValuesVanilla());
        VANILLA_EMC_VALUES_MULTIPLIED.putAll(ConfigManager.getInstance().getEmcValuesVanilla());

        SLIMEFUN_EMC_VALUES.putAll(ConfigManager.getInstance().getEmcValuesSlimefun());
        SLIMEFUN_EMC_VALUES_MULTIPLIED.putAll(ConfigManager.getInstance().getEmcValuesSlimefun());
    }

    private static void setupVanilla() {
        for (Material material : Material.values()) {
            sendDebugMessage(0, "Setup Stage for: " + material.name());
            if (!material.isLegacy() && material.isItem()) {
                final String string = material.name();
                final ItemStack itemStack = new ItemStack(material);
                final Double smallValue = VANILLA_EMC_VALUES.get(string);
                final Double multiValue = VANILLA_EMC_VALUES_MULTIPLIED.get(string);

                if (multiValue != null) {
                    // This item is in the config file, we can skip it
                    sendDebugMessage(
                        0,
                        "This item has already been added either via config or a previous item's calculations: " + multiValue
                    );
                } else {
                    sendDebugMessage(0, "Calculating Multiplied Value.");
                    getItemValueVanilla(itemStack, new ArrayList<>(), 1, true);
                }

                if (smallValue != null) {
                    // This item is in the config file, we can skip it
                    sendDebugMessage(
                        0,
                        "This item has already been added either via config or a previous item's calculations: " + smallValue
                    );
                } else {
                    sendDebugMessage(0, "Calculating Multiplied Value.");
                    getItemValueVanilla(itemStack, new ArrayList<>(), 1, false);
                }
            }
        }
    }

    private static double getItemValueVanilla(@Nonnull ItemStack itemStack,
                                              @Nonnull List<String> history,
                                              int nestingLevel,
                                              boolean multiplier
    ) {
        sendDebugMessage(nestingLevel, "Processing: " + itemStack.getType().name());
        final Double storedValue = multiplier ?
                                   VANILLA_EMC_VALUES_MULTIPLIED.get(itemStack.getType().name()) :
                                   VANILLA_EMC_VALUES.get(itemStack.getType().name());

        if (storedValue != null) {
            // This item has previously been evaluated or is base, so we can return it
            sendDebugMessage(nestingLevel, "A value for this item already exists: " + storedValue);
            return storedValue;
        }

        final String materialName = itemStack.getType().name();

        if (history.contains(materialName)) {
            sendDebugMessage(nestingLevel, "Resulting in a loop, exiting out.");
            // This item was not found in the EMC values but is in the history, so we are looping
            return 0;
        }

        double value = 0;

        final List<Recipe> recipeList = Bukkit.getRecipesFor(itemStack);

        if (recipeList.isEmpty()) {
            sendDebugMessage(
                nestingLevel,
                "There are no recipes and this item is not in the EMC base so it cannot be EMC'd."
            );
            return 0;
        }

        history.add(itemStack.getType().name());
        for (Recipe recipe : recipeList) {
            sendDebugMessage(nestingLevel, "Found a recipe");
            double recipeValue = processRecipeVanilla(recipe, history, nestingLevel + 1, multiplier);
            if (recipeValue > 0) {
                recipeValue = Math.round(recipeValue * 100) / 100.00;
                if (value == 0) {
                    sendDebugMessage(
                        nestingLevel,
                        "Value found and is the first (perhaps only) to be recorded: " + recipeValue
                    );
                    value = recipeValue;
                } else if (recipeValue < value) {
                    sendDebugMessage(
                        nestingLevel,
                        "Value found and is of lesser value which will supersede any previous: " + recipeValue
                    );
                    value = recipeValue;
                }
            } else {
                sendDebugMessage(nestingLevel, "Recipe resulted in 0 EMC meaning it's invalid.");
            }
        }

        if (value > 0) {
            if (multiplier) {
                VANILLA_EMC_VALUES_MULTIPLIED.put(materialName, value);
            } else {
                VANILLA_EMC_VALUES.put(materialName, value);
            }
            sendDebugMessage(nestingLevel, "Final value added of: " + value);
        } else {
            sendDebugMessage(nestingLevel, "Final evaluated value was 0 meaning there are no valid recipes.");
        }

        return value;
    }

    private static double processRecipeVanilla(@Nonnull Recipe recipe,
                                               @Nonnull List<String> history,
                                               int nestingLevel,
                                               boolean multiplier
    ) {
        if (recipe instanceof ShapedRecipe shapedRecipe) {
            return processShapedRecipe(shapedRecipe, history, nestingLevel, multiplier);
        } else if (recipe instanceof ShapelessRecipe shapelessRecipe) {
            return processShapelessRecipe(shapelessRecipe, history, nestingLevel, multiplier);
        } else if (recipe instanceof CookingRecipe<?> cookingRecipe) {
            return processCookingRecipe(cookingRecipe, history, nestingLevel, multiplier);
        } else if (recipe instanceof SmithingRecipe smithingRecipe) {
            return processSmithingRecipe(smithingRecipe, history, nestingLevel, multiplier);
        } else if (recipe instanceof StonecuttingRecipe stonecuttingRecipe) {
            return processStonecuttingRecipe(stonecuttingRecipe, history, nestingLevel, multiplier);
        } else {
            return 0;
        }
    }

    private static double processShapedRecipe(@Nonnull ShapedRecipe shapedRecipe,
                                              @Nonnull List<String> history,
                                              int nestingLevel,
                                              boolean multiplier
    ) {
        double value = 0;
        final Map<Character, Double> valueMap = new HashMap<>();
        for (Map.Entry<Character, ItemStack> entry : shapedRecipe.getIngredientMap().entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            double entryValue = getItemValueVanilla(entry.getValue(), history, nestingLevel + 1, multiplier);
            if (entryValue == 0) {
                // One of the inputs cannot be EMC'd so the recipe cannot be also
                return 0;
            }
            valueMap.put(entry.getKey(), entryValue);
        }
        // If we are here, the whole recipe can be EMC'd
        for (String string : shapedRecipe.getShape()) {
            for (char character : string.toCharArray()) {
                final Double charValue = valueMap.get(character);
                if (charValue != null) {
                    value += valueMap.get(character);
                }
            }
        }
        // Multiply if required
        if (multiplier) {
            value *= MULTIPLIER;
        }
        // Divide by the number of resulting items
        value /= shapedRecipe.getResult().getAmount();
        return value;
    }

    private static double processShapelessRecipe(@Nonnull ShapelessRecipe shapelessRecipe,
                                                 @Nonnull List<String> history,
                                                 int nestingLevel,
                                                 boolean multiplier
    ) {
        double value = 0;
        for (ItemStack itemStack : shapelessRecipe.getIngredientList()) {
            double itemValue = getItemValueVanilla(itemStack, history, nestingLevel + 1, multiplier);
            if (itemValue == 0) {
                // One of the inputs cannot be EMC'd so the recipe cannot be also
                return 0;
            }
            value += itemValue;
        }

        // Multiply if required
        if (multiplier) {
            value *= MULTIPLIER;
        }
        // Divide by the number of resulting items
        value /= shapelessRecipe.getResult().getAmount();
        return value;
    }

    private static double processCookingRecipe(@Nonnull CookingRecipe<?> cookingRecipe,
                                               @Nonnull List<String> history,
                                               int nestingLevel,
                                               boolean multiplier
    ) {
        // Get base value
        double value = getItemValueVanilla(
            cookingRecipe.getInput(),
            history,
            nestingLevel + 1,
            multiplier
        );

        // Multiply if required
        if (multiplier) {
            value *= MULTIPLIER;
        }
        // Divide by the number of resulting items
        value /= cookingRecipe.getResult().getAmount();
        return value;
    }

    private static double processSmithingRecipe(@Nonnull SmithingRecipe smithingRecipe,
                                                @Nonnull List<String> history,
                                                int nestingLevel,
                                                boolean multiplier
    ) {
        // Get base value
        double baseValue = getItemValueVanilla(
            smithingRecipe.getBase().getItemStack(),
            history,
            nestingLevel + 1,
            multiplier
        );
        // Get addition value
        double additionValue = getItemValueVanilla(
            smithingRecipe.getAddition().getItemStack(),
            history,
            nestingLevel + 1,
            multiplier
        );

        // Multiply if required
        if (multiplier) {
            baseValue *= MULTIPLIER;
            additionValue *= MULTIPLIER;
        }
        // Divide by the number of resulting items
        double value = baseValue + additionValue;
        value /= smithingRecipe.getResult().getAmount();
        return value;
    }

    private static double processStonecuttingRecipe(@Nonnull StonecuttingRecipe stonecuttingRecipe,
                                                    @Nonnull List<String> history,
                                                    int nestingLevel,
                                                    boolean multiplier
    ) {
        double value = getItemValueVanilla(
            stonecuttingRecipe.getInput(),
            history,
            nestingLevel + 1,
            multiplier
        );

        // Multiply if required
        if (multiplier) {
            value *= MULTIPLIER;
        }
        // Divine by the number of resulting items
        value /= stonecuttingRecipe.getResult().getAmount();
        return value;
    }

    private static void setupSlimefun() {
        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            final String id = item.getId();
            final Double smallValue = SLIMEFUN_EMC_VALUES.get(id);
            final Double multiValue = SLIMEFUN_EMC_VALUES_MULTIPLIED.get(id);

            sendDebugMessage(0, "Processing: " + id);

            if (multiValue != null) {
                // This item is in the config file, we can skip it
                sendDebugMessage(
                    0,
                    "This item has already been added either via config or a previous item's calculations."
                );
            } else {
                sendDebugMessage(0, "Calculating Multiplied Value.");
                getItemValueSlimefun(item, new ArrayList<>(), 1, true);

            }

            if (smallValue != null) {
                // This item is in the config file, we can skip it
                sendDebugMessage(
                    0,
                    "This item has already been added either via config or a previous item's calculations."
                );
            } else {
                sendDebugMessage(0, "Calculating Basic Value.");
                getItemValueSlimefun(item, new ArrayList<>(), 1, false);

            }
        }
    }

    private static double getItemValueSlimefun(@Nonnull SlimefunItem slimefunItem,
                                               @Nonnull List<String> history,
                                               int nestingLevel,
                                               boolean multiplier
    ) {
        final Double storedValue = multiplier ?
                                   SLIMEFUN_EMC_VALUES_MULTIPLIED.get(slimefunItem.getId()) :
                                   SLIMEFUN_EMC_VALUES.get(slimefunItem.getId());
        sendDebugMessage(0, "Processing: " + slimefunItem.getId());

        if (storedValue != null) {
            // This item has previously been evaluated or is base, so we can return it
            sendDebugMessage(nestingLevel, "A value for this item already exists: " + storedValue);
            return storedValue;
        }

        if (slimefunItem.isDisabled()) {
            sendDebugMessage(nestingLevel, "Item is disabled.");
            return 0;
        }

        if (ConfigManager.isAddonBlacklisted(slimefunItem.getAddon().getName())) {
            sendDebugMessage(nestingLevel, "Item is from a blacklisted addon.");
            return 0;
        }

        final String id = slimefunItem.getId();

        if (history.contains(id)) {
            sendDebugMessage(nestingLevel, "Resulting in a loop, exiting out.");
            // This item was not found in the EMC values but is in the history, so we are looping
            return 0;
        }

        if (!EmcUtils.ALLOWABLE_RECIPE_TYPES.contains(slimefunItem.getRecipeType())) {
            sendDebugMessage(nestingLevel, "Item is from a RecipeType that has not been allowed.");
            return 0;
        }

        double value = 0;

        history.add(id);

        sendDebugMessage(nestingLevel, "Found a recipe");
        double recipeValue = processRecipeSlimefun(
            slimefunItem.getRecipe(),
            slimefunItem.getRecipeOutput().getAmount(),
            history,
            nestingLevel + 1,
            multiplier
        );
        if (recipeValue > 0) {
            recipeValue = Math.round(recipeValue * 100) / 100.00;
            sendDebugMessage(
                nestingLevel,
                "Value found and is of lesser value which will supersede any previous: " + recipeValue
            );
            value = recipeValue;
        } else {
            sendDebugMessage(nestingLevel, "Recipe resulted in 0 EMC meaning it's invalid.");
        }

        if (value > 0) {
            if (multiplier) {
                SLIMEFUN_EMC_VALUES_MULTIPLIED.put(id, value);
            } else {
                SLIMEFUN_EMC_VALUES.put(id, value);
            }
            sendDebugMessage(nestingLevel, "Final value added of: " + value);
        } else {
            sendDebugMessage(nestingLevel, "Final evaluated value was 0 meaning there are no valid recipes.");
        }

        return value;
    }

    private static double processRecipeSlimefun(@Nonnull ItemStack[] recipe,
                                                int outputAmount,
                                                List<String> history,
                                                int nestingLevel,
                                                boolean multiplier
    ) {
        double value = 0;
        for (ItemStack itemStack : recipe) {
            if (itemStack == null) {
                continue;
            }

            final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
            double itemValue;
            if (slimefunItem == null) {
                // Must be a vanilla item
                sendDebugMessage(nestingLevel, "Processing vanilla recipe item: " + itemStack.getType().name());
                itemValue = EmcUtils.getEmcValue(itemStack) * itemStack.getAmount();
            } else {
                sendDebugMessage(nestingLevel, "Processing slimefun recipe item: " + slimefunItem.getId());
                itemValue = getItemValueSlimefun(
                    slimefunItem,
                    history,
                    nestingLevel + 1,
                    multiplier
                ) * itemStack.getAmount();
            }
            if (itemValue == 0) {
                return 0;
            } else {
                value += itemValue;
            }
        }
        return value * (multiplier ? MULTIPLIER : 1) / outputAmount;
    }

    public static Map<String, Double> getVanillaEmcValues() {
        return Map.copyOf(VANILLA_EMC_VALUES);
    }

    public static Map<String, Double> getSlimefunEmcValues() {
        return Map.copyOf(SLIMEFUN_EMC_VALUES);
    }

    public static Map<String, Double> getMultipliedVanillaEmcValues() {
        return Map.copyOf(VANILLA_EMC_VALUES_MULTIPLIED);
    }

    public static Map<String, Double> getMultipliedSlimefunEmcValue() {
        return Map.copyOf(SLIMEFUN_EMC_VALUES_MULTIPLIED);
    }

    public static Map<String, Double> getVanillaEmcValuesFiltered() {
        return vanillaEmcValuesFiltered;
    }

    public static Map<String, Double> getSlimefunEmcValuesFiltered() {
        return slimefunEmcValuesFiltered;
    }

    public static Map<String, Double> getMultipliedVanillaEmcValuesFiltered() {
        return vanillaEmcValuesMultipliedFiltered;
    }

    public static Map<String, Double> getMultipliedSlimefunEmcValuesFiltered() {
        return slimefunEmcValuesMultipliedFiltered;
    }

    private static Map<String, Double> cleanMap(Map<String, Double> map) {
        map.values().removeIf(aDouble -> aDouble == 0.00);
        return map;
    }

    public static void sendDebugMessage(int nestingLevel, String... strings) {
        if (ConfigManager.getInstance().debuggingMessages()) {
            final StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("  ".repeat(nestingLevel));
            for (String string : strings) {
                stringBuilder.append(string);
            }
            EmcTech.getInstance().getLogger().info(stringBuilder.toString());
        }
    }
}
