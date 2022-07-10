
package net.mcreator.skyscastlevania.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.skyscastlevania.item.ClaimhSolaisItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

@SkysCastlevaniaModElements.ModElement.Tag
public class SkysCastlevaniaTabItemGroup extends SkysCastlevaniaModElements.ModElement {
	public SkysCastlevaniaTabItemGroup(SkysCastlevaniaModElements instance) {
		super(instance, 84);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabskys_castlevania_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(ClaimhSolaisItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
