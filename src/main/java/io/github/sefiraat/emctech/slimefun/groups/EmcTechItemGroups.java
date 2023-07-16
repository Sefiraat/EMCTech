package io.github.sefiraat.emctech.slimefun.groups;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.utils.Keys;
import io.github.sefiraat.emctech.utils.Theme;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public final class EmcTechItemGroups {

    private EmcTechItemGroups() {
        throw new IllegalStateException("Utility class");
    }

    public static final MainFlexGroup MAIN = new MainFlexGroup(
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.WRITABLE_BOOK),
            Theme.MAIN.color("EMCTech")
        )
    );

    public static final DummyItemGroup MATERIALS = new DummyItemGroup(
        Keys.newKey("materials"),
        new CustomItemStack(
            new ItemStack(Material.COAL),
            Theme.MAIN.color("EMCTech Crafting Materials")
        )
    );

    public static final DummyItemGroup MACHINES = new DummyItemGroup(
        Keys.newKey("machines"),
        new CustomItemStack(
            new ItemStack(Material.BUBBLE_CORAL_BLOCK),
            Theme.MAIN.color("EMCTech Machinery")
        )
    );

    public static final EmcVanillaDictionaryFlexGroup DICTIONARY_VANILLA = new EmcVanillaDictionaryFlexGroup(
        Keys.newKey("dictionary-vanilla"),
        new CustomItemStack(
            new ItemStack(Material.FEATHER),
            Theme.MAIN.color("EMC Dictionary (Vanilla)")
        )
    );

    public static final EmcSlimefunDictionaryFlexGroup DICTIONARY_SLIMEFUN = new EmcSlimefunDictionaryFlexGroup(
        Keys.newKey("dictionary-slimefun"),
        new CustomItemStack(
            new ItemStack(Material.FEATHER),
            Theme.MAIN.color("EMC Dictionary (Slimefun)")
        )
    );

    static {
        final EmcTech plugin = EmcTech.getInstance();

        // Slimefun Registry
        MAIN.register(plugin);
        MATERIALS.register(plugin);
        MACHINES.register(plugin);
    }
}
