package io.github.sefiraat.emctech.managers;

import io.github.sefiraat.emctech.EmcTech;
import org.bukkit.event.Listener;

public class ListenerManager {

    public ListenerManager() {

    }

    private void addListener(Listener listener) {
        EmcTech.getPluginManager().registerEvents(listener, EmcTech.getInstance());
    }
}
