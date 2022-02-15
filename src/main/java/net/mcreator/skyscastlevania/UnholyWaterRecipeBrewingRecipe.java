
package net.mcreator.skyscastlevania;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;

import net.mcreator.skyscastlevania.item.UnHolyWaterItem;
import net.mcreator.skyscastlevania.item.HolyWaterItem;

@SkysCastlevaniaModElements.ModElement.Tag
public class UnholyWaterRecipeBrewingRecipe extends SkysCastlevaniaModElements.ModElement {
	public UnholyWaterRecipeBrewingRecipe(SkysCastlevaniaModElements instance) {
		super(instance, 137);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}

	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == HolyWaterItem.block;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Blocks.SOUL_SAND.asItem();
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(UnHolyWaterItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
