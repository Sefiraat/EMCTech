package io.github.sefiraat.emctech.utils;

import java.text.MessageFormat;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import dev.sefiraat.sefilib.string.TextUtils;
import net.md_5.bungee.api.ChatColor;

public final class GuiElements {

    private GuiElements() {
        throw new IllegalStateException("Utility class");
    }

    public static final ItemStack TEMPLATE_BACKGROUND = new CustomItemStack(
        Material.BLACK_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Template Item",
        Theme.PASSIVE + "This slot is designated for a single",
        Theme.PASSIVE + "template item."
    );

    public static final ItemStack TEMPLATE_INPUT_CARGO = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Input Slot",
        Theme.PASSIVE + "This slot is for items in.",
        Theme.PASSIVE + "DOES accept from cargo."
    );

    public static final ItemStack TEMPLATE_INPUT_NO_CARGO = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Input Slot",
        Theme.PASSIVE + "This slot is for items in.",
        Theme.PASSIVE + "Does NOT accept from cargo."
    );

    public static final ItemStack TEMPLATE_OUTPUT_CARGO = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Output Slot",
        Theme.PASSIVE + "This slot is for items out.",
        Theme.PASSIVE + "DOES allow taking via cargo."
    );

    public static final ItemStack TEMPLATE_OUTPUT_NO_CARGO = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Input Slot",
        Theme.PASSIVE + "This slot is for items in.",
        Theme.PASSIVE + "Does NOT allow taking via cargo."
    );

    public static final ItemStack INFO_NOT_WORKING = new CustomItemStack(
        Material.RED_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Not Working",
        Theme.PASSIVE + "This machine is not currently working."
    );

    public static final ItemStack INFO_INVALID_ITEM = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Invalid Item",
        Theme.PASSIVE + "This item cannot be converted to/from EMC."
    );

    public static final ItemStack INFO_UNLEARNED_ITEM = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Unlearned Item",
        Theme.PASSIVE + "This item has not yet been learned."
    );

    public static final ItemStack INFO_PLAYER_OFFLINE = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "Owner Offline",
        Theme.PASSIVE + "The owning player is offline."
    );

    public static ItemStack getWorkingOnIcon(@Nonnull String name,
                                             double emcValue,
                                             int powerRequirement,
                                             int currentPower
    ) {
        return new CustomItemStack(
            Material.GREEN_STAINED_GLASS_PANE,
            Theme.PASSIVE + "Working on",
            Theme.CLICK_INFO.applyAsTitle("Item Name", name),
            Theme.CLICK_INFO.applyAsTitle("EMC Value", emcValue),
            Theme.CLICK_INFO.applyAsTitle("Power Required/Operation", powerRequirement),
            Theme.CLICK_INFO.applyAsTitle("Machine Power", currentPower)
        );
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getItemLearnedIcon(@Nonnull ItemStack itemStack,
                                               @Nonnull String name,
                                               double emcValue,
                                               double emcValueLarge
    ) {
        return new CustomItemStack(
            itemStack,
            Theme.SUCCESS + name,
            Theme.CLICK_INFO.applyAsTitle("EMC (Incoming)", EmcUtils.EMC_FORMAT_GUIDE.format(emcValue)),
            Theme.CLICK_INFO.applyAsTitle("EMC (Outgoing)", EmcUtils.EMC_FORMAT_GUIDE.format(emcValueLarge))
        );
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getItemLearnedIcon(@Nonnull Material material,
                                               @Nonnull String name,
                                               double emcValue,
                                               double emcValueLarge
    ) {
        return new CustomItemStack(
            material,
            Theme.SUCCESS + TextUtils.toTitleCase(name),
            Theme.CLICK_INFO.applyAsTitle("EMC (Incoming)", EmcUtils.EMC_FORMAT_GUIDE.format(emcValue)),
            Theme.CLICK_INFO.applyAsTitle("EMC (Outgoing)", EmcUtils.EMC_FORMAT_GUIDE.format(emcValueLarge))
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
