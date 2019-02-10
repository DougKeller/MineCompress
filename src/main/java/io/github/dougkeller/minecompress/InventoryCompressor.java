package io.github.dougkeller.minecompress;

import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.value.ValueContainer;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory;
import org.spongepowered.api.item.inventory.entity.MainPlayerInventory;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipeRegistry;
import org.spongepowered.api.world.World;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public class InventoryCompressor {
    private MainPlayerInventory playerInventory;
    private CraftingRecipeRegistry registry;

    public InventoryCompressor(MainPlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public void logInventory(Inventory inv) {
        for (Inventory i : inv.slots()) {
            Optional<ItemStack> itemStack = i.peek();
            if (itemStack.isPresent()) {
                Minecompress.logger.info(itemStack.get().getType().toString());
                Minecompress.logger.info(itemStack.get().toString());
                CompressabilityChecker checker = new CompressabilityChecker(itemStack.get(), 3);
                Optional<ItemStack> compressed = checker.getCompressed();
                if (compressed.isPresent()) {
                    Minecompress.logger.info(compressed.get().getType().toString());
                    Minecompress.logger.info(compressed.get().toString());
                } else {
                    Minecompress.logger.info("Does not compress");
                }
            }
        }
    }

    public void compress() {
        Minecompress.logger.info("Compressing inventory:");
        Minecompress.logger.info(playerInventory.toString());
        logInventory(playerInventory.getGrid());
        logInventory(playerInventory.getHotbar());
    }
}
