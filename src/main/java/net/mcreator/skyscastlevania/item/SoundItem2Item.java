
package net.mcreator.skyscastlevania.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;

import net.mcreator.skyscastlevania.itemgroup.SkysCastlevaniaTabItemGroup;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

import java.util.List;

@SkysCastlevaniaModElements.ModElement.Tag
public class SoundItem2Item extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:sound_item_2")
	public static final Item block = null;

	public SoundItem2Item(SkysCastlevaniaModElements instance) {
		super(instance, 56);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}

	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, SkysCastlevaniaModElements.sounds.get(new ResourceLocation("skys_castlevania:sinkingoldsanctuary")),
					new Item.Properties().group(SkysCastlevaniaTabItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("sound_item_2");
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Arr. DetectiveSky"));
		}
	}
}
