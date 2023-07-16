package io.github.sefiraat.emctech.managers;

import org.bukkit.event.Listener;

import io.github.sefiraat.emctech.EmcTech;

public class ListenerManager {

    public ListenerManager() {

    }

    private void addListener(Listener listener) {
        EmcTech.getPluginManager().registerEvents(listener, EmcTech.getInstance());
    }
}
