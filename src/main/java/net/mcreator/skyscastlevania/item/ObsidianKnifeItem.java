
package net.mcreator.skyscastlevania.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Blocks;

import net.mcreator.skyscastlevania.procedures.ObsidianKnifeOnPlayerStoppedUsingProcedure;
import net.mcreator.skyscastlevania.procedures.ObsidianKnifeLivingEntityIsHitWithToolProcedure;
import net.mcreator.skyscastlevania.itemgroup.SkysCastlevaniaTabItemGroup;
import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

import java.util.stream.Stream;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.AbstractMap;

@SkysCastlevaniaModElements.ModElement.Tag
public class ObsidianKnifeItem extends SkysCastlevaniaModElements.ModElement {
	@ObjectHolder("skys_castlevania:obsidian_knife")
	public static final Item block = null;

	public ObsidianKnifeItem(SkysCastlevaniaModElements instance) {
		super(instance, 18);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 3f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 5;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.CRYING_OBSIDIAN));
			}
		}, 3, -2.2f, new Item.Properties().group(SkysCastlevaniaTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("A blade used in ancient sacrificial rituals."));
				list.add(new StringTextComponent("Right-click to remove a heart"));
				list.add(new StringTextComponent("and use it for sub-weapons instead."));
			}

			@Override
			public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
				ActionResult<ItemStack> retval = super.onItemRightClick(world, entity, hand);
				ItemStack itemstack = retval.getResult();
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();

				ObsidianKnifeOnPlayerStoppedUsingProcedure.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity),
								new AbstractMap.SimpleEntry<>("itemstack", itemstack))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
				return retval;
			}

			@Override
			public boolean hitEntity(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
				boolean retval = super.hitEntity(itemstack, entity, sourceentity);
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				World world = entity.world;

				ObsidianKnifeLivingEntityIsHitWithToolProcedure
						.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("sourceentity", sourceentity)).collect(HashMap::new,
								(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
				return retval;
			}
		}.setRegistryName("obsidian_knife"));
	}
}
