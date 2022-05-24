package io.github.sefiraat.emctech.slimefun.blocks;

import io.github.sefiraat.emctech.emc.EmcStorage;
import io.github.sefiraat.emctech.slimefun.types.OwnedBlockMenuPreset;
import io.github.sefiraat.emctech.slimefun.types.OwnedVariableTickRateItem;
import io.github.sefiraat.emctech.utils.EmcUtils;
import io.github.sefiraat.emctech.utils.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Dematerializer extends OwnedVariableTickRateItem implements EnergyNetComponent {

    private static final int[] BACKGROUND_SLOTS = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };

    private static final int[] TEMPLATE_BACKGROUND = new int[]{
        12, 14
    };

    private static final int[] INPUT_BACKGROUND = new int[]{
        30, 32
    };

    private static final int TEMPLATE_SLOT = 13;
    private static final int INPUT_SLOT = 31;

    private final int capacity;

    public Dematerializer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int ticksRequired, int capacity) {
        super(itemGroup, item, recipeType, recipe, ticksRequired);
        this.capacity = capacity;
    }

    public Dematerializer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput, int ticksRequired, int capacity) {
        super(itemGroup, item, recipeType, recipe, recipeOutput, ticksRequired);
        this.capacity = capacity;
    }

    @Override
    protected void onEachTickFirst(@Nonnull BlockMenu blockMenu, @Nonnull Block block, @Nonnull SlimefunItem item, @Nonnull Config data) {
        final ItemStack itemStack = blockMenu.getItemInSlot(TEMPLATE_SLOT);

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }
        if (!EmcUtils.canEmc(itemStack)) {
            reject(blockMenu, itemStack);
        } else if (itemStack.getAmount() > 1) {
            rejectOverage(blockMenu, itemStack);
        }
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{INPUT_SLOT};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    public int[] getSlotsToDropOnBreak() {
        return new int[]{INPUT_SLOT, TEMPLATE_SLOT};
    }

    @Override
    protected void onValidTick(@Nonnull BlockMenu blockMenu, @Nonnull Block block, @Nonnull SlimefunItem item, @Nonnull Config data) {
        final ItemStack templateItemStack = blockMenu.getItemInSlot(TEMPLATE_SLOT);
        final ItemStack inputItemStack = blockMenu.getItemInSlot(INPUT_SLOT);

        if (templateItemStack == null || templateItemStack.getType() == Material.AIR) {
            return;
        }

        if (inputItemStack == null || inputItemStack.getType() == Material.AIR) {
            return;
        }

        if (EmcUtils.canEmc(inputItemStack) && SlimefunUtils.isItemSimilar(inputItemStack, templateItemStack, true)) {
            // Item can be EMC'd and matches the given template item
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(inputItemStack);
            final double emcValue = slimefunItem == null ? EmcUtils.getEmcValue(inputItemStack) : EmcUtils.getEmcValue(slimefunItem);
            final int requiredPower = Math.min((int) emcValue / 10, 10000000);
            final Player player = getOwner(block);
            if (player != null && getCharge(block.getLocation()) >= requiredPower) {
                removeCharge(block.getLocation(), requiredPower);
                EmcStorage.addEmc(player, emcValue);
                if (slimefunItem == null) {
                    EmcStorage.learnItem(player, inputItemStack.getType().name(), true);
                } else {
                    EmcStorage.learnItem(player, slimefunItem.getId(), false);
                }
                inputItemStack.setAmount(inputItemStack.getAmount() - 1);
            }

        }

    }

    protected void reject(@Nonnull BlockMenu blockMenu, @Nonnull ItemStack itemStack) {
        final ItemStack rejectedSpawn = itemStack.clone();
        itemStack.setAmount(0);
        blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), rejectedSpawn);
    }

    protected void rejectOverage(@Nonnull BlockMenu blockMenu, @Nonnull ItemStack itemStack) {
        final ItemStack rejectedSpawn = itemStack.clone();
        rejectedSpawn.setAmount(rejectedSpawn.getAmount() - 1);
        itemStack.setAmount(1);
        blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), rejectedSpawn);
    }

    @Override
    public void postRegister() {
        new OwnedBlockMenuPreset(this.getId(), this.getItemName(), this) {

            @Override
            public void init() {
                drawBackground(BACKGROUND_SLOTS);
                for (int i : TEMPLATE_BACKGROUND) {
                    addItem(i, GuiElements.TEMPLATE_BACKGROUND, ChestMenuUtils.getEmptyClickHandler());
                }
                for (int i : INPUT_BACKGROUND) {
                    addItem(i, GuiElements.TEMPLATE_INPUT_CARGO, ChestMenuUtils.getEmptyClickHandler());
                }
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return getInputSlots();
                }
                return new int[0];
            }
        };
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
