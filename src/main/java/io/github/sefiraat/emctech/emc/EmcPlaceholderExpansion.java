package io.github.sefiraat.emctech.emc;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.sefiraat.emctech.utils.EmcUtils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class EmcPlaceholderExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "emctech";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Sefiraat";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("current_emc")) {
            return EmcUtils.EMC_FORMAT.format(EmcStorage.getEmc(player.getUniqueId()));
        }

        if (params.equalsIgnoreCase("learned_amount_vanilla")) {
            return String.valueOf(EmcStorage.getAmountLearned(player.getUniqueId(), true));
        }

        if (params.equalsIgnoreCase("learned_amount_slimefun")) {
            return String.valueOf(EmcStorage.getAmountLearned(player.getUniqueId(), false));
        }

        return null;
    }
}
