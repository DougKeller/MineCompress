package io.github.dougkeller.minecompress;

import org.spongepowered.api.item.inventory.entity.MainPlayerInventory;

public class InventoryCompressor {
    private MainPlayerInventory playerInventory;

    public InventoryCompressor(MainPlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public void compress() {
        Minecompress.logger.info("Compressing inventory:");
        Minecompress.logger.info(playerInventory.toString());
    }
}
