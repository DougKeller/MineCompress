package io.github.dougkeller.minecompress;

import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;
import org.spongepowered.api.world.World;

import java.util.Optional;
import java.util.UUID;

public class RecipeCrafter {
    public static Optional<ItemStack> getResult(ItemStack itemStack, int dimensions) {
        CraftingGridInventory inv = buildInventory(itemStack, dimensions);
        Optional<CraftingRecipe> recipe = inv.getRecipe(getDefaultWorld());

        if (recipe.isPresent()) {
            ItemStack result = recipe.get().getResult(inv).createStack();
            return Optional.of(result);
        }

        return Optional.empty();
    }

    private static CraftingGridInventory buildInventory(ItemStack itemStack, int dimensions) {
        CraftingGridInventory inv = (CraftingGridInventory) Inventory.builder().of(InventoryArchetypes.CRAFTING).build(Minecompress.plugin);
        for (int x = 0; x < dimensions; ++x) {
            for (int y = 0; y < dimensions; ++y) {
                inv.set(x, y, itemStack);
            }
        }

        return inv;
    }

    private static World getDefaultWorld() {
        Server server = Sponge.getServer();
        UUID worldId = server.getDefaultWorld().get().getUniqueId();
        return server.getWorld(worldId).get();
    }
}
