
package net.mcreator.skyscastlevania;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.skyscastlevania.item.HolyWaterItem;
import net.mcreator.skyscastlevania.item.CrucifixItem;

@SkysCastlevaniaModElements.ModElement.Tag
public class HolyWaterRecipeAlt2BrewingRecipe extends SkysCastlevaniaModElements.ModElement {
	public HolyWaterRecipeAlt2BrewingRecipe(SkysCastlevaniaModElements instance) {
		super(instance, 75);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}

	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == Items.SPLASH_POTION;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == CrucifixItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(HolyWaterItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
