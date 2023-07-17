package io.github.sefiraat.emctech.emc;

import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Player;

import io.github.sefiraat.emctech.managers.ConfigManager;

public final class EmcStorage {

    private EmcStorage() {
        throw new IllegalStateException("Utility class");
    }

    @ParametersAreNonnullByDefault
    public static void learnItem(@Nonnull Player player, @Nonnull String itemName, boolean vanilla) {
        learnItem(player.getUniqueId(), itemName, vanilla);
    }

    @ParametersAreNonnullByDefault
    public static void learnItem(@Nonnull UUID player, @Nonnull String itemName, boolean vanilla) {
        final String path = MessageFormat.format(
            "{0}.{1}.{2}.UNLOCKED",
            player,
            vanilla ? "VANILLA" : "SLIMEFUN",
            itemName
        );
        ConfigManager.getPlayerLearnedItems().getFileData().insert(path, true);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasLearnedItem(@Nonnull Player player, @Nonnull String itemName, boolean vanilla) {
        return hasLearnedItem(player.getUniqueId(), itemName, vanilla);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasLearnedItem(@Nonnull UUID player, @Nonnull String itemName, boolean vanilla) {
        final String path = MessageFormat.format(
            "{0}.{1}.{2}.UNLOCKED",
            player,
            vanilla ? "VANILLA" : "SLIMEFUN",
            itemName
        );
        return ConfigManager.getPlayerLearnedItems().getBoolean(path);
    }

    @ParametersAreNonnullByDefault
    public static int getAmountLearned(@Nonnull Player player, boolean vanilla) {
        return getAmountLearned(player.getUniqueId(), vanilla);
    }

    @ParametersAreNonnullByDefault
    public static int getAmountLearned(@Nonnull UUID player, boolean vanilla) {
        final String path = MessageFormat.format("{0}.{1}", player, vanilla ? "VANILLA" : "SLIMEFUN");
        final Map<?, ?> learnedItemsMap = ConfigManager.getPlayerLearnedItems().getMap(path);
        return learnedItemsMap.size();
    }

    @ParametersAreNonnullByDefault
    public static void setEmc(@Nonnull Player player, double emcValue) {
        setEmc(player.getUniqueId(), emcValue);
    }

    @ParametersAreNonnullByDefault
    public static void setEmc(@Nonnull UUID player, double emcValue) {
        final String path = MessageFormat.format("{0}.EMC", player);
        ConfigManager.getPlayerEmc().getFileData().insert(path, emcValue);
    }

    @ParametersAreNonnullByDefault
    public static double getEmc(@Nonnull Player player) {
        return getEmc(player.getUniqueId());
    }

    @ParametersAreNonnullByDefault
    public static double getEmc(@Nonnull UUID player) {
        final String path = MessageFormat.format("{0}.EMC", player);
        return ConfigManager.getPlayerEmc().getDouble(path);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasEnoughEmc(@Nonnull Player player, double emcValue) {
        return hasEnoughEmc(player.getUniqueId(), emcValue);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasEnoughEmc(@Nonnull UUID player, double emcValue) {
        final double currentValue = getEmc(player);
        return currentValue >= emcValue;
    }

    @ParametersAreNonnullByDefault
    public static void addEmc(@Nonnull Player player, double emcValue) {
        addEmc(player.getUniqueId(), emcValue);
    }

    @ParametersAreNonnullByDefault
    public static void addEmc(@Nonnull UUID player, double emcValue) {
        final String path = MessageFormat.format("{0}.EMC", player);
        double currentValue = getEmc(player);
        double newValue = currentValue + emcValue;
        if (Double.isInfinite(newValue)) {
            newValue = Double.MAX_VALUE;
        }
        ConfigManager.getPlayerEmc().getFileData().insert(path, newValue);
    }

    @ParametersAreNonnullByDefault
    public static void removeEmc(@Nonnull Player player, double emcValue) {
        removeEmc(player.getUniqueId(), emcValue);
    }

    @ParametersAreNonnullByDefault
    public static void removeEmc(@Nonnull UUID player, double emcValue) {
        final String path = MessageFormat.format("{0}.EMC", player);
        double currentValue = getEmc(player);
        double newValue = currentValue - emcValue;
        ConfigManager.getPlayerEmc().getFileData().insert(path, newValue);
    }
}

