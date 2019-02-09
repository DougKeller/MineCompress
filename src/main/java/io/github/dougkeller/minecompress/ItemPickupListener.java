package io.github.dougkeller.minecompress;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;

public class ItemPickupListener {
    private Minecompress plugin;

    public ItemPickupListener(Minecompress plugin) {
        this.plugin = plugin;
    }

    @Listener
    public void onItemPickup(ChangeInventoryEvent.Pickup event) {
        this.plugin.getLogger().info(event.toString());
    }
}
