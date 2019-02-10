package io.github.dougkeller.minecompress;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipeRegistry;

import java.util.Optional;

public class CompressabilityChecker {
    private CraftingRecipeRegistry registry;
    private ItemStack sourceStack;
    private int dimensions;

    public CompressabilityChecker(ItemStack itemStack, int dimensions) {
        this.sourceStack = itemStack.copy();
        this.sourceStack.setQuantity(1);
        this.dimensions = dimensions;
        this.registry = Sponge.getRegistry().getCraftingRecipeRegistry();
    }

    public Optional<ItemStack> getCompressed() {
        Optional<ItemStack> compressed = RecipeCrafter.getResult(sourceStack, dimensions);
        if (!compressed.isPresent()) {
            return Optional.empty();
        }

        Optional<ItemStack> decompressed = RecipeCrafter.getResult(compressed.get(), 1);
        if (!decompressed.isPresent()) {
            return Optional.empty();
        }

        boolean returnsSameAmount = decompressed.get().getQuantity() == dimensions * dimensions;
        if (!returnsSameAmount) {
            return Optional.empty();
        }

        decompressed.get().setQuantity(1);
        boolean returnsSameItem = decompressed.get().equalTo(sourceStack);
        return returnsSameItem ? compressed : Optional.empty();
    }
}
