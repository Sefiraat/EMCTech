package io.github.sefiraat.emctech.slimefun.types;

import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.block.Block;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public abstract class OwnedBlockMenuPresetNode extends BlockMenuPreset {

    @Nonnull
    private final OwnedVariableTickRateNode ownedVariableTickRateItem;

    protected OwnedBlockMenuPresetNode(@Nonnull String id,
                                       @Nonnull String title,
                                       @Nonnull OwnedVariableTickRateNode ownedVariableTickRateItem
    ) {
        super(id, title);
        this.ownedVariableTickRateItem = ownedVariableTickRateItem;
    }

    protected OwnedBlockMenuPresetNode(@Nonnull String id,
                                       @Nonnull String title,
                                       boolean universal,
                                       @Nonnull OwnedVariableTickRateNode ownedVariableTickRateItem
    ) {
        super(id, title, universal);
        this.ownedVariableTickRateItem = ownedVariableTickRateItem;
    }

    @Override
    public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
        final String playerString = BlockStorage.getLocationInfo(b.getLocation(), "owner");

        if (playerString == null) {
            return;
        }

        ownedVariableTickRateItem.addCache(
            b,
            new OwnedVariableTickRateNode.OwnedBlockCache(
                UUID.fromString(playerString),
                ownedVariableTickRateItem.getTicksRequired()
            )
        );
    }
}
