package io.github.sefiraat.emctech;


import co.aikar.commands.PaperCommandManager;
import io.github.sefiraat.emctech.commands.EmcTechMain;
import io.github.sefiraat.emctech.emc.EmcCalculator;
import io.github.sefiraat.emctech.managers.ConfigManager;
import io.github.sefiraat.emctech.managers.ListenerManager;
import io.github.sefiraat.emctech.managers.RunnableManager;
import io.github.sefiraat.emctech.managers.SupportedPluginManager;
import io.github.sefiraat.emctech.slimefun.items.EmcItems;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;

public class EmcTech extends JavaPlugin implements SlimefunAddon {
    private static EmcTech instance;

    private final String username;
    private final String repo;
    private final String branch;

    private ConfigManager configManager;
    private ListenerManager listenerManager;
    private SupportedPluginManager supportedPluginManager;
    private RunnableManager runnableManager;
    private PaperCommandManager commandManager;

    public EmcTech() {
        this.username = "Sefiraat";
        this.repo = "EMCTech";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("           EMCTech by Sefiraat          ");
        getLogger().info("########################################");

        saveDefaultConfig();
        tryUpdate();

        this.configManager = new ConfigManager();
        this.supportedPluginManager = new SupportedPluginManager();
        this.runnableManager = new RunnableManager();
        this.commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new EmcTechMain());

        EmcItems.setup();
        EmcCalculator.setup();
    }

    @Override
    public void onDisable() {
        ConfigManager.getInstance().saveAll();
    }

    public void tryUpdate() {
        if (getConfig().getBoolean("auto-update")
            && getDescription().getVersion().startsWith("DEV")
        ) {
            String updateLocation = MessageFormat.format("{0}/{1}/{2}", this.username, this.repo, this.branch);
            GitHubBuildsUpdater updater = new GitHubBuildsUpdater(this, getFile(), updateLocation);
            updater.start();
        }
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    public static EmcTech getInstance() {
        return EmcTech.instance;
    }

    @Nonnull
    public static PluginManager getPluginManager() {
        return EmcTech.getInstance().getServer().getPluginManager();
    }

    public static ConfigManager getConfigManager() {
        return EmcTech.getInstance().configManager;
    }

    public static ListenerManager getListenerManager() {
        return EmcTech.getInstance().listenerManager;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return EmcTech.getInstance().supportedPluginManager;
    }

    public static RunnableManager getRunnableManager() {
        return EmcTech.getInstance().runnableManager;
    }
}
