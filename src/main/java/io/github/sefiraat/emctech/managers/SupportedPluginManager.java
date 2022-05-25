package io.github.sefiraat.emctech.managers;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

public class SupportedPluginManager {

    private static SupportedPluginManager instance;

    private boolean networks;
    private boolean dankTech2;
    private boolean infinityExpansion;

    public SupportedPluginManager() {
        Validate.isTrue(instance == null, "Cannot create a new instance of the SupportedPluginManager");
        instance = this;
        setup();
    }

    private void setup() {
        this.networks = Bukkit.getPluginManager().isPluginEnabled("Networks");
        this.dankTech2 = Bukkit.getPluginManager().isPluginEnabled("DankTech2");
        this.infinityExpansion = Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion");
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

    public static SupportedPluginManager getInstance() {
        return instance;
    }
}
