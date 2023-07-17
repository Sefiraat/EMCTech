package io.github.sefiraat.emctech.utils;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;

import io.github.sefiraat.emctech.EmcTech;

public final class Keys {

    private Keys() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey(EmcTech.getInstance(), value);
    }
}
