package io.github.sefiraat.emctech.utils;

import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.emc.EmcGenerator;
import io.github.sefiraat.emctech.managers.SupportedPluginManager;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class EmcUtils {

    private EmcUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final DecimalFormat EMC_FORMAT = new DecimalFormat("#,###.00");

    public static final List<RecipeType> ALLOWABLE_RECIPE_TYPES = new ArrayList<>();

    static {
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.ENHANCED_CRAFTING_TABLE);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.SMELTERY);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.GOLD_PAN);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.ORE_CRUSHER);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.ORE_WASHER);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.PRESSURE_CHAMBER);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.HEATED_PRESSURE_CHAMBER);
        ALLOWABLE_RECIPE_TYPES.add(RecipeType.COMPRESSOR);

        final EmcTech plugin = EmcTech.getInstance();

        if (SupportedPluginManager.isDankTech2()) {
            EmcUtils.addValidRecipeType(SlimefunItem.getById("DK2_PACK_1").getRecipeType());
            plugin.getLogger().info("Adding extended recipes for DankTech2");
        }
        if (SupportedPluginManager.isInfinityExpansion()) {
            EmcUtils.addValidRecipeType(SlimefunItem.getById("INFINITY_DUST_EXTRACTOR").getRecipeType());
            plugin.getLogger().info("Adding extended recipes for Infinity Expansion");
        }

    }

    public static boolean canEmc(@Nonnull ItemStack itemStack) {
        return !itemStack.hasItemMeta() || SlimefunItem.getByItem(itemStack) != null;
    }

    public static double getEmcValue(@Nonnull ItemStack itemStack) {
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if (slimefunItem == null) {
            return EmcGenerator.getVanillaEmcValues().getOrDefault(itemStack.getType().name(), 0.00);
        } else {
            return EmcGenerator.getSlimefunEmcValues().getOrDefault(slimefunItem.getId(), 0.00);
        }
    }

    public static double getEmcValue(@Nonnull String string) {
        return EmcGenerator.getVanillaEmcValues().getOrDefault(string, 0.00);
    }

    public static double getEmcValue(@Nonnull String string, boolean vanilla) {
        if (vanilla) {
            return EmcGenerator.getVanillaEmcValues().getOrDefault(string, 0.00);
        } else {
            return EmcGenerator.getSlimefunEmcValues().getOrDefault(string, 0.00);
        }
    }

    public static double getEmcValue(@Nonnull SlimefunItem slimefunItem) {
        return EmcGenerator.getSlimefunEmcValues().getOrDefault(slimefunItem.getId(), 0.00);
    }

    public static double getEmcValueMultiplied(@Nonnull ItemStack itemStack) {
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if (slimefunItem == null) {
            return EmcGenerator.getMultipliedVanillaEmcValues().getOrDefault(itemStack.getType().name(), 0.00);
        } else {
            return EmcGenerator.getMultipliedSlimefunEmcValues().getOrDefault(slimefunItem.getId(), 0.00);
        }
    }

    public static double getEmcValueMultiplied(@Nonnull String string) {
        return EmcGenerator.getMultipliedVanillaEmcValues().getOrDefault(string, 0.00);
    }

    public static double getEmcValueMultiplied(@Nonnull String string, boolean vanilla) {
        if (vanilla) {
            return EmcGenerator.getMultipliedVanillaEmcValues().getOrDefault(string, 0.00);
        } else {
            return EmcGenerator.getMultipliedSlimefunEmcValues().getOrDefault(string, 0.00);
        }
    }

    public static double getEmcValueMultiplied(@Nonnull SlimefunItem slimefunItem) {
        return EmcGenerator.getMultipliedSlimefunEmcValues().getOrDefault(slimefunItem.getId(), 0.00);
    }

    public static void addValidRecipeType(@Nonnull RecipeType recipeType) {
        ALLOWABLE_RECIPE_TYPES.add(recipeType);
    }

}
