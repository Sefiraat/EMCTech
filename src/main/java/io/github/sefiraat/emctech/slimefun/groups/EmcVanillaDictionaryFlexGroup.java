package io.github.sefiraat.emctech.slimefun.groups;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.sefiraat.emctech.emc.EmcCalculator;
import io.github.sefiraat.emctech.emc.EmcStorage;
import io.github.sefiraat.emctech.utils.EmcUtils;
import io.github.sefiraat.emctech.utils.GuiElements;
import io.github.sefiraat.emctech.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

import net.md_5.bungee.api.ChatColor;

/**
 * @noinspection deprecation
 */
public class EmcVanillaDictionaryFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;
    private static final int GUIDE_STATS = 7;

    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    @ParametersAreNonnullByDefault
    public EmcVanillaDictionaryFlexGroup(NamespacedKey key, ItemStack item) {
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
        final ChestMenu chestMenu = new ChestMenu(Theme.MAIN.getColor() + "EMC Dictionary");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        chestMenu.setEmptySlotsClickable(false);
        chestMenu.addMenuOpeningHandler((player) -> player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F));
        setupPage(p, profile, mode, chestMenu, 1);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page) {
        final List<String> entries = new ArrayList<>(EmcCalculator.getVanillaEmcValuesFiltered().keySet());
        final int numberOfEntries = entries.size();
        final int totalPages = (int) Math.ceil(numberOfEntries / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, numberOfEntries);

        entries.sort(Comparator.comparing(definition -> definition));

        final List<String> entriesSubList = entries.subList(start, end);

        reapplyFooter(player, profile, mode, menu, page, totalPages);

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, EmcTechItemGroups.MAIN, mode, 1);
            return false;
        });

        // Stats
        menu.replaceExistingItem(GUIDE_STATS, getPlayerInfoStack(player, numberOfEntries));
        menu.addMenuClickHandler(GUIDE_STATS, ChestMenuUtils.getEmptyClickHandler());

        for (int i = 0; i < 36; i++) {
            final int slot = i + 9;

            if (i + 1 <= entriesSubList.size()) {
                final String entry = entriesSubList.get(i);
                final boolean learned = EmcStorage.hasLearnedItem(player, entry, true);

                if (mode == SlimefunGuideMode.CHEAT_MODE || learned) {
                    menu.replaceExistingItem(
                        slot,
                        GuiElements.getItemLearnedIcon(
                            Material.valueOf(entry),
                            entry,
                            EmcUtils.getEmcValue(entry),
                            EmcUtils.getEmcValueMultiplied(entry)
                        )
                    );
                } else {
                    menu.replaceExistingItem(slot, GuiElements.getItemNotLearnedIcon(entry));
                }
            } else {
                menu.replaceExistingItem(slot, null);
            }
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    @ParametersAreNonnullByDefault
    private void reapplyFooter(Player p,
                               PlayerProfile profile,
                               SlimefunGuideMode mode,
                               ChestMenu menu,
                               int page,
                               int totalPages
    ) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (player1, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage >= 1) {
                setupPage(player1, profile, mode, menu, previousPage);
                player1.playSound(player1.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (player1, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage <= totalPages) {
                setupPage(player1, profile, mode, menu, nextPage);
                player1.playSound(player1.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
            }
            return false;
        });
    }

    @ParametersAreNonnullByDefault
    private ItemStack getPlayerInfoStack(Player player, int size) {
        final ChatColor color = Theme.CLICK_INFO.getColor();
        final ChatColor passive = Theme.PASSIVE.getColor();
        final List<String> lore = new ArrayList<>();

        lore.add(MessageFormat.format(
            "{0}Vanilla Items Learned: {1}{2}/{3}",
            color,
            passive,
            EmcStorage.getAmountLearned(player, true),
            size
        ));

        return new CustomItemStack(
            Material.TARGET,
            Theme.MAIN.getColor() + "Learned Items",
            lore
        );
    }
}
