
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

import net.mcreator.skyscastlevania.itemgroup.SkysCastlevaniaTabItemGroup;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

import java.util.List;

@SkysCastlevaniaModElements.ModElement.Tag
public class CrissaegrimItem extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:crissaegrim")
	public static final Item block = null;

	public CrissaegrimItem(SkysCastlevaniaModElements instance) {
		super(instance, 30);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 2000;
			}

			public float getEfficiency() {
				return 9f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 5;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(GreenStoneItem.block));
			}
		}, 3, 16f, new Item.Properties().group(SkysCastlevaniaTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("This sword's name means \"strong blessed wind\" in Elvish."));
				list.add(new StringTextComponent("Also known as Crissaegrim."));
			}
		}.setRegistryName("crissaegrim"));
	}
}
