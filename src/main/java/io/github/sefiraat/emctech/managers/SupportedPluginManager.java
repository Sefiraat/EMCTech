package io.github.sefiraat.emctech.managers;

import org.bukkit.Bukkit;

import com.google.common.base.Preconditions;

import io.github.sefiraat.emctech.emc.EmcPlaceholderExpansion;

public class SupportedPluginManager {

    private static SupportedPluginManager instance;

    private boolean networks;
    private boolean dankTech2;
    private boolean infinityExpansion;
    private boolean placeholderApi;

    public SupportedPluginManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the SupportedPluginManager");
        instance = this;
        setup();
    }

    private void setup() {
        this.networks = Bukkit.getPluginManager().isPluginEnabled("Networks");
        this.dankTech2 = Bukkit.getPluginManager().isPluginEnabled("DankTech2");
        this.infinityExpansion = Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion");
        this.placeholderApi = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");

        if (this.placeholderApi) {
            new EmcPlaceholderExpansion().register();
        }
    }

    public static boolean isNetworks() {
        return instance.networks;
    }

    public static void setNetworks(boolean networks) {
        instance.networks = networks;
    }

    public static boolean isDankTech2() {
        return instance.dankTech2;
    }

    public static void setDankTech2(boolean dankTech2) {
        instance.dankTech2 = dankTech2;
    }

    public static boolean isInfinityExpansion() {
        return instance.infinityExpansion;
    }

    public static void setInfinityExpansion(boolean infinityExpansion) {
        instance.infinityExpansion = infinityExpansion;
    }

    public static boolean isPlaceholderApi() {
        return instance.placeholderApi;
    }

    public static void setPlaceholderApi(boolean placeholderApi) {
        instance.placeholderApi = placeholderApi;
    }

    public static SupportedPluginManager getInstance() {
        return instance;
    }
}
