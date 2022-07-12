
package net.mcreator.skyscastlevania.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.skyscastlevania.itemgroup.SkysCastlevaniaTabItemGroup;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

@SkysCastlevaniaModElements.ModElement.Tag
public class RawSilverItem extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:raw_silver")
	public static final Item block = null;

	public RawSilverItem(SkysCastlevaniaModElements instance) {
		super(instance, 313);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(SkysCastlevaniaTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("raw_silver");
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
