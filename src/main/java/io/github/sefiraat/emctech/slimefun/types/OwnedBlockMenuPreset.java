package io.github.sefiraat.emctech.slimefun.types;

import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.block.Block;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public abstract class OwnedBlockMenuPreset extends BlockMenuPreset {

    @Nonnull
    private final OwnedVariableTickRateItem ownedVariableTickRateItem;

    protected OwnedBlockMenuPreset(@Nonnull String id,
                                   @Nonnull String title,
                                   @Nonnull OwnedVariableTickRateItem ownedVariableTickRateItem
    ) {
        super(id, title);
        this.ownedVariableTickRateItem = ownedVariableTickRateItem;
    }

    protected OwnedBlockMenuPreset(@Nonnull String id,
                                   @Nonnull String title,
                                   boolean universal,
                                   @Nonnull OwnedVariableTickRateItem ownedVariableTickRateItem
    ) {
        super(id, title, universal);
        this.ownedVariableTickRateItem = ownedVariableTickRateItem;
    }

    @Override
    public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block block) {
        final String playerString = BlockStorage.getLocationInfo(block.getLocation(), "owner");

        if (playerString == null) {
            return;
        }

        ownedVariableTickRateItem.addCache(
            block,
            new OwnedVariableTickRateItem.OwnedBlockCache(
                UUID.fromString(playerString),
                ownedVariableTickRateItem.getTicksRequired()
            )
        );
    }
}
