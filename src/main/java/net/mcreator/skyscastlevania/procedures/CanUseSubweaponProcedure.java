package net.mcreator.skyscastlevania.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.potion.CurseEffectPotionEffect;
import net.mcreator.skyscastlevania.item.TwoStoneItem;
import net.mcreator.skyscastlevania.item.ThreeStoneItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;
import java.util.Collection;

public class CanUseSubweaponProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure CanUseSubweapon!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(TwoStoneItem.block)) : false)
				&& 1 >= (entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive
				|| ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ThreeStoneItem.block)) : false)
						&& 2 >= (entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive
				|| 0 >= (entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive) {
			if (!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == CurseEffectPotionEffect.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity))) {
				return true;
			}
		}
		return false;
	}
}
