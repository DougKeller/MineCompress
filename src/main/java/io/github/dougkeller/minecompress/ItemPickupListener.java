package io.github.dougkeller.minecompress;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.entity.MainPlayerInventory;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import com.google.inject.Inject;
import org.slf4j.Logger;

public class ItemPickupListener {
    private Minecompress plugin;

    @Inject
    private Logger logger;

    @Listener
    public void onItemPickup(ChangeInventoryEvent.Pickup event) {
        MainPlayerInventory inv = fetchMainPlayerInventory((event.getTargetInventory()));
        InventoryCompressor compressor = new InventoryCompressor(inv);
        compressor.compress();
    }

    private MainPlayerInventory fetchMainPlayerInventory(Inventory inventory) {
        Inventory root = inventory.root();
        return root.query(QueryOperationTypes.INVENTORY_TYPE.of(MainPlayerInventory.class)).first();
    }
}
