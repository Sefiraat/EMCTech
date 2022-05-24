package io.github.sefiraat.emctech.managers;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

public class SupportedPluginManager {

    private static SupportedPluginManager instance;

    private boolean networks;

    public SupportedPluginManager() {
        Validate.isTrue(instance == null, "Cannot create a new instance of the SupportedPluginManager");
        instance = this;
        setup();
    }

    private void setup() {
        this.networks = Bukkit.getPluginManager().isPluginEnabled("Networks");
    }

    public static boolean isNetworks() {
        return instance.networks;
    }

    public static void setNetworks(boolean networks) {
        instance.networks = networks;
    }

    public static SupportedPluginManager getInstance() {
        return instance;
    }
}
