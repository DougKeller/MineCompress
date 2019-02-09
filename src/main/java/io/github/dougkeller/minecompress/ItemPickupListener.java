package io.github.dougkeller.minecompress;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.entity.MainPlayerInventory;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

public class ItemPickupListener {
    @Listener
    public void onItemPickup(ChangeInventoryEvent.Pickup event) {
        InventoryCompressor compressor = new InventoryCompressor(fetchMainInventory(event));
        compressor.compress();
    }

    private MainPlayerInventory fetchMainInventory(ChangeInventoryEvent event) {
        Inventory root = event.getTargetInventory().root();
        return (MainPlayerInventory) root.query(QueryOperationTypes.INVENTORY_TYPE.of(MainPlayerInventory.class));
    }
}
