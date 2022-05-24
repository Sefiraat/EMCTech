package io.github.sefiraat.emctech.emc;

import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.managers.ConfigManager;
import io.github.sefiraat.emctech.utils.EmcUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.StonecuttingRecipe;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EmcGenerator {

    private static final double MULTIPLIER = 1.1;

    private static final Map<String, Double> VANILLA_EMC_VALUES = new HashMap<>();
    private static final Map<String, Double> SLIMEFUN_EMC_VALUES = new HashMap<>();
    private static final Map<String, Double> VANILLA_EMC_VALUES_MULTIPLIED = new HashMap<>();
    private static final Map<String, Double> SLIMEFUN_EMC_VALUES_MULTIPLIED = new HashMap<>();

    private EmcGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static void setup() {
        addBaseValues();
        setupVanilla();
        setupSlimefun();
    }

    private static void addBaseValues() {
        VANILLA_EMC_VALUES.putAll(ConfigManager.getInstance().getEmcValuesVanilla());
        VANILLA_EMC_VALUES_MULTIPLIED.putAll(ConfigManager.getInstance().getEmcValuesVanilla());

        SLIMEFUN_EMC_VALUES.putAll(ConfigManager.getInstance().getEmcValuesSlimefun());
        SLIMEFUN_EMC_VALUES_MULTIPLIED.putAll(ConfigManager.getInstance().getEmcValuesSlimefun());
    }

    private static void setupVanilla() {
        for (Material material : Material.values()) {
            if (!material.isLegacy() && material.isItem()) {
                final String string = material.name();
                if (VANILLA_EMC_VALUES.containsKey(string)) {
                    // This item is in the config file, we can skip it
                    continue;
                }
                final ItemStack itemStack = new ItemStack(material);
                getItemValueVanilla(itemStack, new ArrayList<>(), 0, true);
                getItemValueVanilla(itemStack, new ArrayList<>(), 0, false);
            }
        }
    }

    private static double getItemValueVanilla(@Nonnull ItemStack itemStack, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
        sendDebugMessage(nestingLevel, "Processing: " + itemStack.getType().name());
        final Double storedValue = multiplier ? VANILLA_EMC_VALUES_MULTIPLIED.get(itemStack.getType().name()) : VANILLA_EMC_VALUES.get(itemStack.getType().name());

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
            sendDebugMessage(nestingLevel, "There are no recipes and this item is not in the EMC base so it cannot be EMC'd.");
            return 0;
        }

        history.add(itemStack.getType().name());
        for (Recipe recipe : recipeList) {
            sendDebugMessage(nestingLevel, "Found a recipe");
            double recipeValue = processRecipeVanilla(recipe, history, nestingLevel + 1, multiplier);
            if (recipeValue > 0) {
                recipeValue = Math.round(recipeValue * 100) / 100.00;
                if (value == 0) {
                    sendDebugMessage(nestingLevel, "Value found and is the first (perhaps only) to be recorded: " + recipeValue);
                    value = recipeValue;
                } else if (recipeValue < value) {
                    sendDebugMessage(nestingLevel, "Value found and is of lesser value which will supersede any previous: " + recipeValue);
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

    private static double processRecipeVanilla(@Nonnull Recipe recipe, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
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

    private static double processShapedRecipe(@Nonnull ShapedRecipe shapedRecipe, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
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
        return (value * (multiplier ? MULTIPLIER : 1)) / shapedRecipe.getResult().getAmount();
    }

    private static double processShapelessRecipe(@Nonnull ShapelessRecipe shapelessRecipe, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
        double value = 0;
        for (ItemStack itemStack : shapelessRecipe.getIngredientList()) {
            double itemValue = getItemValueVanilla(itemStack, history, nestingLevel + 1, multiplier);
            if (itemValue == 0) {
                // One of the inputs cannot be EMC'd so the recipe cannot be also
                return 0;
            }
            value += itemValue;
        }
        return (value * (multiplier ? MULTIPLIER : 1)) / shapelessRecipe.getResult().getAmount();
    }

    private static double processCookingRecipe(@Nonnull CookingRecipe<?> cookingRecipe, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
        return (getItemValueVanilla(cookingRecipe.getInput(), history, nestingLevel + 1, multiplier) * (multiplier ? MULTIPLIER : 1)) / cookingRecipe.getResult().getAmount();
    }

    private static double processSmithingRecipe(@Nonnull SmithingRecipe smithingRecipe, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
        return (getItemValueVanilla(smithingRecipe.getBase().getItemStack(), history, nestingLevel + 1, multiplier) * (multiplier ? MULTIPLIER : 1)) / smithingRecipe.getResult().getAmount();
    }

    private static double processStonecuttingRecipe(@Nonnull StonecuttingRecipe stonecuttingRecipe, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
        return (getItemValueVanilla(stonecuttingRecipe.getInput(), history, nestingLevel + 1, multiplier) * (multiplier ? MULTIPLIER : 1)) / stonecuttingRecipe.getResult().getAmount();
    }

    private static void setupSlimefun() {
        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            if (EmcUtils.ALLOWABLE_RECIPE_TYPES.contains(item.getRecipeType())) {
                final String string = item.getId();
                if (SLIMEFUN_EMC_VALUES.containsKey(string)) {
                    // This item is in the config file, we can skip it
                    continue;
                }
                getItemValueSlimefun(item, new ArrayList<>(), 0, true);
                getItemValueSlimefun(item, new ArrayList<>(), 0, false);
            }
        }
    }

    private static double getItemValueSlimefun(@Nonnull SlimefunItem slimefunItem, @Nonnull List<String> history, int nestingLevel, boolean multiplier) {
        sendDebugMessage(nestingLevel, "Processing: " + slimefunItem.getId());
        final Double storedValue = multiplier ? SLIMEFUN_EMC_VALUES_MULTIPLIED.get(slimefunItem.getId()) : SLIMEFUN_EMC_VALUES.get(slimefunItem.getId());

        if (storedValue != null) {
            // This item has previously been evaluated or is base, so we can return it
            sendDebugMessage(nestingLevel, "A value for this item already exists: " + storedValue);
            return storedValue;
        }

        final String id = slimefunItem.getId();

        if (history.contains(id)) {
            sendDebugMessage(nestingLevel, "Resulting in a loop, exiting out.");
            // This item was not found in the EMC values but is in the history, so we are looping
            return 0;
        }

        double value = 0;

        history.add(id);

        sendDebugMessage(nestingLevel, "Found a recipe");
        double recipeValue = processRecipeSlimefun(slimefunItem.getRecipe(), slimefunItem.getRecipeOutput().getAmount(), history, nestingLevel + 1, multiplier);
        if (recipeValue > 0) {
            recipeValue = Math.round(recipeValue * 100) / 100.00;
            sendDebugMessage(nestingLevel, "Value found and is of lesser value which will supersede any previous: " + recipeValue);
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

    private static double processRecipeSlimefun(@Nonnull ItemStack[] recipe, int outputAmount, List<String> history, int nestingLevel, boolean multiplier) {
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
                itemValue = getItemValueSlimefun(slimefunItem, history, nestingLevel + 1, multiplier) * itemStack.getAmount();
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
        return VANILLA_EMC_VALUES;
    }

    public static Map<String, Double> getSlimefunEmcValues() {
        return SLIMEFUN_EMC_VALUES;
    }

    public static Map<String, Double> getMultipliedVanillaEmcValues() {
        return VANILLA_EMC_VALUES_MULTIPLIED;
    }

    public static Map<String, Double> getMultipliedSlimefunEmcValues() {
        return SLIMEFUN_EMC_VALUES_MULTIPLIED;
    }

    public static void sendDebugMessage(int nestingLevel, String... strings) {
        if (ConfigManager.getInstance().debuggingMessages()) {
            final StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(" ".repeat(nestingLevel));
            for (String string : strings) {
                stringBuilder.append(string);
            }
            EmcTech.getInstance().getLogger().info(stringBuilder.toString());
        }
    }
}
