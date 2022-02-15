
package net.mcreator.skyscastlevania.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Blocks;

import net.mcreator.skyscastlevania.itemgroup.SkysCastlevaniaTabItemGroup;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

import java.util.List;

@SkysCastlevaniaModElements.ModElement.Tag
public class RahabsFrostItem extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:rahabs_frost")
	public static final Item block = null;

	public RahabsFrostItem(SkysCastlevaniaModElements instance) {
		super(instance, 15);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 500;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Blocks.ICE), new ItemStack(Blocks.PACKED_ICE), new ItemStack(Blocks.FROSTED_ICE),
						new ItemStack(Blocks.BLUE_ICE));
			}
		}, 3, -2.4f, new Item.Properties().group(SkysCastlevaniaTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("A sword wrought from the fang of Rahab"));
				list.add(new StringTextComponent("the sea-demon."));
			}
		}.setRegistryName("rahabs_frost"));
	}
}
