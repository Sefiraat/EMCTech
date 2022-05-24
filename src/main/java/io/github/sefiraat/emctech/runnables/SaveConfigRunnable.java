package io.github.sefiraat.emctech.runnables;

import io.github.sefiraat.emctech.managers.ConfigManager;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveConfigRunnable extends BukkitRunnable {

    @Override
    public void run() {
        ConfigManager.getInstance().saveAll();
    }
}
