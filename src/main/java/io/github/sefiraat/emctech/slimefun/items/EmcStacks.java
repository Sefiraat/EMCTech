package io.github.sefiraat.emctech.slimefun.items;

import io.github.sefiraat.emctech.utils.Theme;
import io.github.sefiraat.sefilib.itemstacks.ItemStackGenerators;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EmcStacks {

    private EmcStacks() {
        throw new IllegalStateException("Utility class");
    }

    // Vanilla
    public static final ItemStack VANILLA_COAL = new ItemStack(Material.COAL);

    // Materials
    public static final SlimefunItemStack UNORTHODOX_COAL;
    public static final SlimefunItemStack UNORTHODOX_COAL_DUST;
    public static final SlimefunItemStack TIGHTLY_PACKED_UNORTHODOX_COAL;
    public static final SlimefunItemStack UNORTHODOX_COAL_BLOCK;
    public static final SlimefunItemStack DEVIANT_COAL;
    public static final SlimefunItemStack DEVIANT_COAL_DUST;
    public static final SlimefunItemStack TIGHTLY_PACKED_DEVIANT_COAL;
    public static final SlimefunItemStack DEVIANT_COAL_BLOCK;
    public static final SlimefunItemStack DIVERGENT_COAL;
    public static final SlimefunItemStack DIVERGENT_COAL_DUST;
    public static final SlimefunItemStack TIGHTLY_PACKED_DIVERGENT_COAL;
    public static final SlimefunItemStack DIVERGENT_COAL_BLOCK;
    public static final SlimefunItemStack ANOMALOUS_COAL;
    public static final SlimefunItemStack ANOMALOUS_COAL_DUST;
    public static final SlimefunItemStack TIGHTLY_PACKED_ANOMALOUS_COAL;
    public static final SlimefunItemStack ANOMALOUS_COAL_BLOCK;
    public static final SlimefunItemStack PERFECTED_COAL;

    // Components
    public static final SlimefunItemStack UNORTHODOX_FRAME;
    public static final SlimefunItemStack DEVIANT_FRAME;
    public static final SlimefunItemStack DIVERGENT_FRAME;
    public static final SlimefunItemStack ANOMALOUS_FRAME;
    public static final SlimefunItemStack PERFECTED_FRAME;
    public static final SlimefunItemStack UNORTHODOX_MACHINE_FRAME;
    public static final SlimefunItemStack DEVIANT_MACHINE_FRAME;
    public static final SlimefunItemStack DIVERGENT_MACHINE_FRAME;
    public static final SlimefunItemStack ANOMALOUS_MACHINE_FRAME;
    public static final SlimefunItemStack PERFECTED_MACHINE_FRAME;

    // Machines
    public static final SlimefunItemStack EMC_DEMATERIALIZER_1;
    public static final SlimefunItemStack EMC_DEMATERIALIZER_2;
    public static final SlimefunItemStack EMC_DEMATERIALIZER_3;
    public static final SlimefunItemStack EMC_DEMATERIALIZER_4;
    public static final SlimefunItemStack EMC_DEMATERIALIZER_5;

    public static final SlimefunItemStack EMC_MATERIALIZER_1;
    public static final SlimefunItemStack EMC_MATERIALIZER_2;
    public static final SlimefunItemStack EMC_MATERIALIZER_3;
    public static final SlimefunItemStack EMC_MATERIALIZER_4;
    public static final SlimefunItemStack EMC_MATERIALIZER_5;

    public static final SlimefunItemStack EMC_NETWORK_MATERIALIZER;

    static {

        // region Crafting Materials

        UNORTHODOX_COAL = Theme.themedSlimefunItemStack(
            "ETC_UNORTHODOX_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Unorthodox Coal",
            "The molecules in this coal have been",
            "rearranged. Seemingly with little effect."
        );

        UNORTHODOX_COAL_DUST = Theme.themedSlimefunItemStack(
            "ETC_UNORTHODOX_COAL_DUST",
            ItemStackGenerators.createEnchantedItemStack(Material.GUNPOWDER, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Unorthodox Coal Dust",
            "Unorthodox Coal crushed into a fine dust."
        );

        TIGHTLY_PACKED_UNORTHODOX_COAL = Theme.themedSlimefunItemStack(
            "ETC_TIGHTLY_PACKED_UNORTHODOX_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.POLISHED_BLACKSTONE_BUTTON, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Unorthodox Coal Dust (Packed)",
            "Unorthodox Coal packed into a tight form."
        );

        UNORTHODOX_COAL_BLOCK = Theme.themedSlimefunItemStack(
            "ETC_UNORTHODOX_COAL_BLOCK",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL_BLOCK, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Unorthodox Coal Block",
            "A block of Unorthodox Coal."
        );

        DEVIANT_COAL = Theme.themedSlimefunItemStack(
            "ETC_DEVIANT_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Deviant Coal",
            "The molecules in this coal have been",
            "rearranged. The faintest power can be",
            "felt from within."
        );

        DEVIANT_COAL_DUST = Theme.themedSlimefunItemStack(
            "ETC_DEVIANT_COAL_DUST",
            ItemStackGenerators.createEnchantedItemStack(Material.GUNPOWDER, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Deviant Coal Dust",
            "Deviant Coal crushed into a fine dust."
        );

        TIGHTLY_PACKED_DEVIANT_COAL = Theme.themedSlimefunItemStack(
            "ETC_TIGHTLY_PACKED_DEVIANT_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.POLISHED_BLACKSTONE_BUTTON, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Deviant Coal Dust (Packed)",
            "Deviant Coal packed into a tight form."
        );

        DEVIANT_COAL_BLOCK = Theme.themedSlimefunItemStack(
            "ETC_DEVIANT_COAL_BLOCK",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL_BLOCK, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Deviant Coal Block",
            "A block of Deviant Coal."
        );

        DIVERGENT_COAL = Theme.themedSlimefunItemStack(
            "ETC_DIVERGENT_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Divergent Coal",
            "The molecules in this coal have been",
            "rearranged. A strong power can be",
            "felt from within."
        );

        DIVERGENT_COAL_DUST = Theme.themedSlimefunItemStack(
            "ETC_DIVERGENT_COAL_DUST",
            ItemStackGenerators.createEnchantedItemStack(Material.GUNPOWDER, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Divergent Coal Dust",
            "Divergent Coal crushed into a fine dust."
        );

        TIGHTLY_PACKED_DIVERGENT_COAL = Theme.themedSlimefunItemStack(
            "ETC_TIGHTLY_PACKED_DIVERGENT_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.POLISHED_BLACKSTONE_BUTTON, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Divergent Coal Dust (Packed)",
            "Divergent Coal packed into a tight form."
        );

        DIVERGENT_COAL_BLOCK = Theme.themedSlimefunItemStack(
            "ETC_DIVERGENT_COAL_BLOCK",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL_BLOCK, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Divergent Coal Block",
            "A block of Divergent Coal."
        );

        ANOMALOUS_COAL = Theme.themedSlimefunItemStack(
            "ETC_ANOMALOUS_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Anomalous Coal",
            "The molecules in this coal have been",
            "rearranged. An intense power can be",
            "felt from within."
        );

        ANOMALOUS_COAL_DUST = Theme.themedSlimefunItemStack(
            "ETC_ANOMALOUS_COAL_DUST",
            ItemStackGenerators.createEnchantedItemStack(Material.GUNPOWDER, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Anomalous Coal Dust",
            "Anomalous Coal crushed into a fine dust."
        );

        TIGHTLY_PACKED_ANOMALOUS_COAL = Theme.themedSlimefunItemStack(
            "ETC_TIGHTLY_PACKED_ANOMALOUS_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.POLISHED_BLACKSTONE_BUTTON, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Anomalous Coal Dust (Packed)",
            "Anomalous Coal packed into a tight form."
        );

        ANOMALOUS_COAL_BLOCK = Theme.themedSlimefunItemStack(
            "ETC_ANOMALOUS_COAL_BLOCK",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL_BLOCK, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Anomalous Coal Block",
            "A block of Anomalous Coal."
        );

        PERFECTED_COAL = Theme.themedSlimefunItemStack(
            "ETC_PERFECTED_COAL",
            ItemStackGenerators.createEnchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Perfected Coal",
            "The molecules in this coal have been",
            "rearranged. The radiating power can",
            "barely be measured."
        );

        // endregion

        // region Components

        UNORTHODOX_FRAME = Theme.themedSlimefunItemStack(
            "ETC_UNORTHADOX_FRAME",
            new ItemStack(Material.BLACK_STAINED_GLASS),
            Theme.CRAFTING,
            "Unorthadox Frame",
            "A simple, empty, frame used for making",
            "covalent machinery."
        );

        DEVIANT_FRAME = Theme.themedSlimefunItemStack(
            "ETC_DEVIANT_FRAME",
            new ItemStack(Material.BLACK_STAINED_GLASS),
            Theme.CRAFTING,
            "Deviant Frame",
            "A basic, empty, frame used for making",
            "covalent machinery."
        );

        DIVERGENT_FRAME = Theme.themedSlimefunItemStack(
            "ETC_DIVERGENT_FRAME",
            new ItemStack(Material.BLACK_STAINED_GLASS),
            Theme.CRAFTING,
            "Divergent Frame",
            "An advanced, empty, frame used for making",
            "covalent machinery."
        );

        ANOMALOUS_FRAME = Theme.themedSlimefunItemStack(
            "ETC_ANOMALOUS_FRAME",
            new ItemStack(Material.BLACK_STAINED_GLASS),
            Theme.CRAFTING,
            "Anomalous Frame",
            "A superior, empty, frame used for making",
            "covalent machinery."
        );

        PERFECTED_FRAME = Theme.themedSlimefunItemStack(
            "ETC_PERFECTED_FRAME",
            new ItemStack(Material.BLACK_STAINED_GLASS),
            Theme.CRAFTING,
            "Perfected Frame",
            "A perfect, empty, frame used for making",
            "covalent machinery."
        );

        UNORTHODOX_MACHINE_FRAME = Theme.themedSlimefunItemStack(
            "ETC_UNORTHADOX_MACHINE_FRAME",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.CRAFTING,
            "Unorthadox Machine Frame",
            "A simple machine frame suitable",
            "for housing covalent tech."
        );

        DEVIANT_MACHINE_FRAME = Theme.themedSlimefunItemStack(
            "ETC_DEVIANT_MACHINE_FRAME",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.CRAFTING,
            "Deviant Machine Frame",
            "A basic machine frame suitable",
            "for housing covalent tech."
        );

        DIVERGENT_MACHINE_FRAME = Theme.themedSlimefunItemStack(
            "ETC_DIVERGENT_MACHINE_FRAME",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.CRAFTING,
            "Divergent Machine Frame",
            "An advanced machine frame suitable",
            "for housing covalent tech."
        );

        ANOMALOUS_MACHINE_FRAME = Theme.themedSlimefunItemStack(
            "ETC_ANOMALOUS_MACHINE_FRAME",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.CRAFTING,
            "Anomalous Machine Frame",
            "A superior machine frame suitable",
            "for housing covalent tech."
        );

        PERFECTED_MACHINE_FRAME = Theme.themedSlimefunItemStack(
            "ETC_PERFECTED_MACHINE_FRAME",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.CRAFTING,
            "Perfected Machine Frame",
            "A perfect machine frame suitable",
            "for housing covalent tech."
        );

        // endregion

        // region Machines

        EMC_DEMATERIALIZER_1 = Theme.themedSlimefunItemStack(
            "ETC_DEMATERIALIZER_1",
            new ItemStack(Material.LIGHT_GRAY_WOOL),
            Theme.MACHINE,
            "Dematerializer <➊>",
            "This machine will slowly dematerialize items",
            "and put the resulting EMC into the owner's",
            "available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 5 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "1,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10"
        );

        EMC_DEMATERIALIZER_2 = Theme.themedSlimefunItemStack(
            "ETC_DEMATERIALIZER_2",
            new ItemStack(Material.LIME_WOOL),
            Theme.MACHINE,
            "Dematerializer <➋>",
            "This machine will slowly dematerialize items",
            "and put the resulting EMC into the owner's",
            "available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 4 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "10,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10"
        );

        EMC_DEMATERIALIZER_3 = Theme.themedSlimefunItemStack(
            "ETC_DEMATERIALIZER_3",
            new ItemStack(Material.LIGHT_BLUE_WOOL),
            Theme.MACHINE,
            "Dematerializer <➌>",
            "This machine will slowly dematerialize items",
            "and put the resulting EMC into the owner's",
            "available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 3 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "100,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10"
        );

        EMC_DEMATERIALIZER_4 = Theme.themedSlimefunItemStack(
            "ETC_DEMATERIALIZER_4",
            new ItemStack(Material.PURPLE_WOOL),
            Theme.MACHINE,
            "Dematerializer <➍>",
            "This machine will slowly dematerialize items",
            "and put the resulting EMC into the owner's",
            "available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 2 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "1,000,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10"
        );

        EMC_DEMATERIALIZER_5 = Theme.themedSlimefunItemStack(
            "ETC_DEMATERIALIZER_5",
            new ItemStack(Material.RED_WOOL),
            Theme.MACHINE,
            "Dematerializer <➎>",
            "This machine will slowly dematerialize items",
            "and put the resulting EMC into the owner's",
            "available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per S/F Tick",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "10,000,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10 (Caps at capacity)"
        );

        EMC_MATERIALIZER_1 = Theme.themedSlimefunItemStack(
            "ETC_MATERIALIZER_1",
            new ItemStack(Material.LIGHT_GRAY_CONCRETE),
            Theme.MACHINE,
            "Materializer <➊>",
            "This machine will slowly create items",
            "from the owner's available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 5 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "1,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value"
        );

        EMC_MATERIALIZER_2 = Theme.themedSlimefunItemStack(
            "ETC_MATERIALIZER_2",
            new ItemStack(Material.LIME_CONCRETE),
            Theme.MACHINE,
            "Materializer <➋>",
            "This machine will slowly create items",
            "from the owner's available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 4 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "10,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value"
        );

        EMC_MATERIALIZER_3 = Theme.themedSlimefunItemStack(
            "ETC_MATERIALIZER_3",
            new ItemStack(Material.LIGHT_BLUE_CONCRETE),
            Theme.MACHINE,
            "Materializer <➌>",
            "This machine will slowly create items",
            "from the owner's available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 3 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "100,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value"
        );

        EMC_MATERIALIZER_4 = Theme.themedSlimefunItemStack(
            "ETC_MATERIALIZER_4",
            new ItemStack(Material.PURPLE_CONCRETE),
            Theme.MACHINE,
            "Materializer <➍>",
            "This machine will slowly create items",
            "from the owner's available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per 2 S/F Ticks",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "1,000,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10"
        );

        EMC_MATERIALIZER_5 = Theme.themedSlimefunItemStack(
            "ETC_MATERIALIZER_5",
            new ItemStack(Material.RED_CONCRETE),
            Theme.MACHINE,
            "Materializer <➎>",
            "This machine will slowly create items",
            "from the owner's available EMC pool.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per S/F Tick",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "10,000,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10 (Caps at capacity)"
        );

        EMC_NETWORK_MATERIALIZER = Theme.themedSlimefunItemStack(
            "ETC_NETWORK_MATERIALIZER",
            new ItemStack(Material.YELLOW_STAINED_GLASS),
            Theme.MACHINE,
            "Network EMC Materializer",
            "This machine will slowly create items",
            "from the owner's available EMC pool.",
            "Items can be seen within your Network.",
            "",
            Theme.CLICK_INFO + "Rate: " + Theme.PASSIVE + "1 item per S/F Tick",
            Theme.CLICK_INFO + "Power Capacity: " + Theme.PASSIVE + "10,000,000",
            Theme.CLICK_INFO + "Power Consumption: " + Theme.PASSIVE + "Item EMC Value / 10 (Caps at capacity)"
        );

        // endregion
    }
}
