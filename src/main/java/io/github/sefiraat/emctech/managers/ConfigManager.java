package io.github.sefiraat.emctech.managers;

import de.leonhard.storage.Json;
import io.github.sefiraat.emctech.EmcTech;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {

    private static ConfigManager instance;

    private final Json playerEmc;
    private final Json playerLearnedItems;

    private final List<String> blacklistedAddons = new ArrayList<>();

    public ConfigManager() {
        instance = this;

        this.playerEmc = new Json("player_emc.yml", "plugins/EMCTech");
        this.playerLearnedItems = new Json("learned_items.yml", "plugins/EMCTech");

        final ConfigurationSection section = EmcTech.getInstance().getConfig().getConfigurationSection("blacklisted-addons");
        for (String key : section.getKeys(false)) {
            if (section.getBoolean(key)) {
                blacklistedAddons.add(key);
            }
        }

    }

    /**
     * @noinspection ResultOfMethodCallIgnored
     */
    @Nonnull
    private FileConfiguration getConfig(String fileName, boolean updateWithDefaults) {
        final EmcTech plugin = EmcTech.getInstance();
        final File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, true);
        }
        final FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
            if (updateWithDefaults) {
                updateConfig(config, file, fileName);
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }

    private void updateConfig(@Nonnull FileConfiguration config, File file, String fileName) throws IOException {
        final InputStream inputStream = EmcTech.getInstance().getResource(fileName);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final YamlConfiguration defaults = YamlConfiguration.loadConfiguration(reader);
        config.addDefaults(defaults);
        config.options().copyDefaults(true);
        config.save(file);
    }

    public void saveAll() {
        EmcTech.getInstance().getLogger().info("EMCTech saving data.");
        EmcTech.getInstance().saveConfig();
        this.playerEmc.write();
        this.playerLearnedItems.write();
    }

    public void saveConfig(@Nonnull FileConfiguration configuration, String filename) {
        File file = new File(EmcTech.getInstance().getDataFolder(), filename);
        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Map<String, Double> getEmcValuesVanilla() {
        final ConfigurationSection section = EmcTech.getInstance().getConfig().getConfigurationSection("emc-values.vanilla");
        if (section == null) {
            EmcTech.getInstance().getLogger().severe("The EMC Base values config for Vanilla cannot be found.");
            return Collections.emptyMap();
        }
        return getEmcValues(section);
    }

    public Map<String, Double> getEmcValuesSlimefun() {
        final ConfigurationSection section = EmcTech.getInstance().getConfig().getConfigurationSection("emc-values.slimefun");
        if (section == null) {
            EmcTech.getInstance().getLogger().severe("The EMC Base values config for Slimefun cannot be found.");
            return Collections.emptyMap();
        }
        return getEmcValues(section);
    }

    public Map<String, Double> getEmcValues(@Nonnull ConfigurationSection section) {
        Map<String, Double> values = new HashMap<>();
        for (String s : section.getKeys(false)) {
            Double emcValue = section.getDouble(s);
            values.put(s, emcValue);
        }
        return values;
    }

    public boolean debuggingMessages() {
        return EmcTech.getInstance().getConfig().getBoolean("debug-messages", false);
    }

    public List<String> getBlacklistedAddons() {
        return this.blacklistedAddons;
    }

    public static boolean isAddonBlacklisted(@Nonnull String addonName) {
        for (String blacklistedAddon : instance.getBlacklistedAddons()) {
            if (blacklistedAddon.equals(addonName)) {
                return true;
            }
        }
        return false;
    }

    @Nonnull
    public static ConfigManager getInstance() {
        return instance;
    }

    public static Json getPlayerEmc() {
        return instance.playerEmc;
    }

    public static Json getPlayerLearnedItems() {
        return instance.playerLearnedItems;
    }
}
