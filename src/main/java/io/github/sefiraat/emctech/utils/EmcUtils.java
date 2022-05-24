package io.github.sefiraat.emctech.utils;

import io.github.sefiraat.emctech.emc.EmcGenerator;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.List;

public final class EmcUtils {

    private EmcUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final DecimalFormat EMC_FORMAT = new DecimalFormat("#,###.00");

    public static final List<RecipeType> ALLOWABLE_RECIPE_TYPES = List.of(
        RecipeType.ENHANCED_CRAFTING_TABLE,
        RecipeType.SMELTERY,
        RecipeType.GOLD_PAN,
        RecipeType.ORE_CRUSHER,
        RecipeType.ORE_WASHER,
        RecipeType.PRESSURE_CHAMBER,
        RecipeType.HEATED_PRESSURE_CHAMBER,
        RecipeType.COMPRESSOR
    );

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

}
