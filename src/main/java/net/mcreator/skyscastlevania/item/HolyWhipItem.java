
package net.mcreator.skyscastlevania.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.client.util.ITooltipFlag;

import net.mcreator.skyscastlevania.itemgroup.SkysCastlevaniaTabItemGroup;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

import java.util.List;

@SkysCastlevaniaModElements.ModElement.Tag
public class HolyWhipItem extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:holy_whip")
	public static final Item block = null;

	public HolyWhipItem(SkysCastlevaniaModElements instance) {
		super(instance, 28);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1000;
			}

			public float getEfficiency() {
				return 0f;
			}

			public float getAttackDamage() {
				return 0.5f;
			}

			public int getHarvestLevel() {
				return 0;
			}

			public int getEnchantability() {
				return 5;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.LEATHER));
			}
		}, 1, -2.2f, new Item.Properties().group(SkysCastlevaniaTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("A ritually blessed leather whip."));
			}
		}.setRegistryName("holy_whip"));
	}
}
