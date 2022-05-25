package io.github.sefiraat.emctech.utils;

import io.github.sefiraat.sefilib.string.StringUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;

public final class GuiElements {

    private GuiElements() {
        throw new IllegalStateException("Utility class");
    }

    public static final CustomItemStack TEMPLATE_BACKGROUND = new CustomItemStack(
        Material.BLACK_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Template Item",
        Theme.PASSIVE + "This slot is designated for a single",
        Theme.PASSIVE + "template item."
    );

    public static final CustomItemStack TEMPLATE_INPUT_CARGO = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Input Slot",
        Theme.PASSIVE + "This slot is for items in.",
        Theme.PASSIVE + "DOES accept from cargo."
    );

    public static final CustomItemStack TEMPLATE_INPUT_NO_CARGO = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Input Slot",
        Theme.PASSIVE + "This slot is for items in.",
        Theme.PASSIVE + "Does NOT accept from cargo."
    );

    public static final CustomItemStack TEMPLATE_OUTPUT_CARGO = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Output Slot",
        Theme.PASSIVE + "This slot is for items out.",
        Theme.PASSIVE + "DOES allow taking via cargo."
    );

    public static final CustomItemStack TEMPLATE_OUTPUT_NO_CARGO = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Input Slot",
        Theme.PASSIVE + "This slot is for items in.",
        Theme.PASSIVE + "Does NOT allow taking via cargo."
    );

    @ParametersAreNonnullByDefault
    public static ItemStack getItemLearnedIcon(@Nonnull Material material, @Nonnull String name, double emcValue, double emcValueLarge) {
        return new CustomItemStack(
            material,
            Theme.SUCCESS + StringUtils.toTitleCase(name),
            Theme.CLICK_INFO + "EMC (Incoming): " + Theme.PASSIVE + EmcUtils.EMC_FORMAT.format(emcValue),
            Theme.CLICK_INFO + "EMC (Outgoing): " + Theme.PASSIVE + EmcUtils.EMC_FORMAT.format(emcValueLarge)
        );
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getItemLearnedIcon(@Nonnull ItemStack itemStack, @Nonnull String name, double emcValue, double emcValueLarge) {
        return new CustomItemStack(
            itemStack,
            Theme.SUCCESS + name,
            Theme.CLICK_INFO + "EMC (Incoming): " + Theme.PASSIVE + EmcUtils.EMC_FORMAT.format(emcValue),
            Theme.CLICK_INFO + "EMC (Outgoing): " + Theme.PASSIVE + EmcUtils.EMC_FORMAT.format(emcValueLarge)
        );
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getItemNotLearnedIcon(@Nonnull String name) {
        return new CustomItemStack(
            Material.BARRIER,
            Theme.ERROR + name,
            MessageFormat.format("{0}{1}NOT LEARNED", Theme.RESEARCH.getColor(), ChatColor.BOLD),
            Theme.ERROR + "This item has not yet been learned."
        );
    }

}
