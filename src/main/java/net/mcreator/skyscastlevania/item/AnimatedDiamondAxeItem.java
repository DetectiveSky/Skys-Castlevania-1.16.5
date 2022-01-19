
package net.mcreator.skyscastlevania.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

@SkysCastlevaniaModElements.ModElement.Tag
public class AnimatedDiamondAxeItem extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:animated_diamond_axe")
	public static final Item block = null;

	public AnimatedDiamondAxeItem(SkysCastlevaniaModElements instance) {
		super(instance, 293);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("animated_diamond_axe");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
