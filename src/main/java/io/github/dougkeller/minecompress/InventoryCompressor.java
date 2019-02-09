package io.github.dougkeller.minecompress;

import org.spongepowered.api.item.inventory.entity.MainPlayerInventory;

import com.google.inject.Inject;
import org.slf4j.Logger;

public class InventoryCompressor {
    private MainPlayerInventory playerInventory;

    @Inject
    private Logger logger;

    public InventoryCompressor(MainPlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public void compress() {
        this.logger.info("Compressing inventory:");
        this.logger.info(playerInventory.toString());
    }
}
