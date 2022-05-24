package io.github.sefiraat.emctech.utils;

import io.github.sefiraat.emctech.EmcTech;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public final class Keys {

    private Keys() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey(EmcTech.getInstance(), value);
    }
}
