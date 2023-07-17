package io.github.sefiraat.emctech.slimefun.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class OwnedVariableTickRateItem extends SlimefunItem {
    private final int ticksRequired;
    protected Map<Location, OwnedBlockCache> ownedBlockCacheMap = new HashMap<>();

    protected OwnedVariableTickRateItem(ItemGroup itemGroup,
                                        SlimefunItemStack item,
                                        RecipeType recipeType,
                                        ItemStack[] recipe,
                                        int ticksRequired
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.ticksRequired = ticksRequired;
    }

    protected OwnedVariableTickRateItem(ItemGroup itemGroup,
                                        SlimefunItemStack item,
                                        RecipeType recipeType,
                                        ItemStack[] recipe,
                                        @Nullable ItemStack recipeOutput,
                                        int ticksRequired
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        this.ticksRequired = ticksRequired;
    }

    @Override
    public void preRegister() {
        addItemHandler(
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                    ownedBlockCacheMap.put(
                        event.getBlock().getLocation(),
                        new OwnedBlockCache(event.getPlayer(), ticksRequired)
                    );
                    final BlockMenu blockMenu = BlockStorage.getInventory(event.getBlock());
                    BlockStorage.addBlockInfo(
                        blockMenu.getBlock(),
                        "owner",
                        event.getPlayer().getUniqueId().toString()
                    );
                    whenPlaced(event);
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                    final BlockMenu blockMenu = BlockStorage.getInventory(e.getBlock());
                    if (blockMenu == null) {
                        return;
                    }
                    blockMenu.dropItems(blockMenu.getLocation(), getSlotsToDropOnBreak());
                    whenBroken(e, item, drops);
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(@Nonnull Block block, @Nonnull SlimefunItem item, @Nonnull Config data) {
                    final OwnedBlockCache cache = ownedBlockCacheMap.get(block.getLocation());
                    if (cache == null) {
                        return;
                    }

                    final BlockMenu blockMenu = BlockStorage.getInventory(block);
                    if (blockMenu == null) {
                        return;
                    }

                    onEachTickFirst(blockMenu, block, item, data);
                    if (cache.tryTick()) {
                        onValidTick(blockMenu, block, item, data);
                    }
                    onEachTickLast(blockMenu, block, item, data);
                }
            }
        );
    }

    public abstract int[] getInputSlots();

    public abstract int[] getOutputSlots();

    public abstract int[] getSlotsToDropOnBreak();

    /**
     * Fires when the block is placed but after the owner cache is created.
     *
     * @param e
     */
    protected void whenPlaced(@Nonnull BlockPlaceEvent e) {

    }

    /**
     * Fires when the block is placed but after the owner cache is created.
     *
     * @param e
     */
    protected void whenBroken(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {

    }

    /**
     * Fires when the block has successfully ticked, this takes into account the ticks required parameter (not every SF tick)
     *
     * @param block
     * @param item
     * @param data
     */
    protected void onValidTick(@Nonnull BlockMenu blockMenu,
                               @Nonnull Block block,
                               @Nonnull SlimefunItem item,
                               @Nonnull Config data
    ) {

    }

    /**
     * Fires when the block has ticked, this does not take into account the ticks required parameter (every SF tick)
     * This will fire before the 'successful' tick should it coincide.
     *
     * @param block
     * @param item
     * @param data
     */
    protected void onEachTickFirst(@Nonnull BlockMenu blockMenu,
                                   @Nonnull Block block,
                                   @Nonnull SlimefunItem item,
                                   @Nonnull Config data
    ) {

    }

    /**
     * Fires when the block has ticked, this does not take into account the ticks required parameter (every SF tick)
     * This will fire after the 'successful' tick should it coincide.
     *
     * @param block
     * @param item
     * @param data
     */
    protected void onEachTickLast(@Nonnull BlockMenu blockMenu,
                                  @Nonnull Block block,
                                  @Nonnull SlimefunItem item,
                                  @Nonnull Config data
    ) {

    }

    public int getTicksRequired() {
        return ticksRequired;
    }

    @Nullable
    public OwnedBlockCache getCache(@Nonnull Location location) {
        return ownedBlockCacheMap.get(location);
    }

    @Nullable
    public OwnedBlockCache getCache(@Nonnull Block block) {
        return getCache(block.getLocation());
    }

    public void addCache(@Nonnull Block block, @Nonnull OwnedBlockCache ownedBlockCache) {
        ownedBlockCacheMap.put(block.getLocation(), ownedBlockCache);
    }

    @Nullable
    public Player getOwner(@Nonnull Block block) {
        return getOwner(block.getLocation());
    }

    @Nullable
    public Player getOwner(@Nonnull Location location) {
        final OwnedBlockCache cache = getCache(location);
        return cache == null ? null : cache.getOwningPlayer();
    }

    public static class OwnedBlockCache {

        @Nonnull
        private final UUID ownerUuid;
        private final int ticksRequired;
        private int currentTick;

        public OwnedBlockCache(@Nonnull UUID uuid, int ticksRequired) {
            this.ownerUuid = uuid;
            this.ticksRequired = ticksRequired;
        }

        public OwnedBlockCache(@Nonnull Player player, int ticksRequired) {
            this.ownerUuid = player.getUniqueId();
            this.ticksRequired = ticksRequired;
        }

        @Nonnull
        public UUID getOwnerUuid() {
            return ownerUuid;
        }

        @Nullable
        public Player getOwningPlayer() {
            return Bukkit.getPlayer(ownerUuid);
        }

        @Nullable
        public OfflinePlayer getOwningPlayerOffline() {
            return Bukkit.getOfflinePlayer(ownerUuid);
        }

        public boolean isOwnerOnline() {
            final Player player = getOwningPlayer();
            return player != null && player.isOnline();
        }

        public boolean tryTick() {
            currentTick++;
            if (currentTick >= ticksRequired) {
                currentTick = 0;
                return true;
            }
            return false;
        }

        public int getTicksRequired() {
            return ticksRequired;
        }

        public int getCurrentTick() {
            return currentTick;
        }

        public void setCurrentTick(int currentTick) {
            this.currentTick = currentTick;
        }
    }
}
