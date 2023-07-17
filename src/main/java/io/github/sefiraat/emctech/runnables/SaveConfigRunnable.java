package io.github.sefiraat.emctech.runnables;

import org.bukkit.scheduler.BukkitRunnable;

import io.github.sefiraat.emctech.managers.ConfigManager;

public class SaveConfigRunnable extends BukkitRunnable {

    @Override
    public void run() {
        ConfigManager.getInstance().saveAll();
    }
}
