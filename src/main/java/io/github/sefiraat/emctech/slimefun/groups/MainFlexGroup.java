package io.github.sefiraat.emctech.slimefun.groups;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.sefiraat.emctech.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

/**
 * @noinspection deprecation
 */
public class MainFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int MATERIALS = 9;
    private static final int MECHANISMS = 10;
    private static final int DICTIONARY_VANILLA = 11;
    private static final int DICTIONARY_SLIMEFUN = 12;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    public MainFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(Theme.MAIN.getColor() + "EMC Tech");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ((player1, i, itemStack, clickAction) -> false));
        }

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openMainMenu(profile, mode, 1);
            return false;
        });

        // Machines
        menu.replaceExistingItem(MATERIALS, EmcTechItemGroups.MATERIALS.getItem(player));
        menu.addMenuClickHandler(MATERIALS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, EmcTechItemGroups.MATERIALS, mode, 1)
        );

        // Machines
        menu.replaceExistingItem(MECHANISMS, EmcTechItemGroups.MACHINES.getItem(player));
        menu.addMenuClickHandler(MECHANISMS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, EmcTechItemGroups.MACHINES, mode, 1)
        );

        // EMC Dictionary (Vanilla)
        menu.replaceExistingItem(DICTIONARY_VANILLA, EmcTechItemGroups.DICTIONARY_VANILLA.getItem(player));
        menu.addMenuClickHandler(DICTIONARY_VANILLA, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, EmcTechItemGroups.DICTIONARY_VANILLA, mode, 1)
        );

        // EMC Dictionary (Slimefun)
        menu.replaceExistingItem(DICTIONARY_SLIMEFUN, EmcTechItemGroups.DICTIONARY_SLIMEFUN.getItem(player));
        menu.addMenuClickHandler(DICTIONARY_SLIMEFUN, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, EmcTechItemGroups.DICTIONARY_SLIMEFUN, mode, 1)
        );
    }

    @ParametersAreNonnullByDefault
    public boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add(this, 1);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }
}
