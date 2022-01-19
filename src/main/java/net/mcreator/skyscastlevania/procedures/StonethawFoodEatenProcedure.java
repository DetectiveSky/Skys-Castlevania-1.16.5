package net.mcreator.skyscastlevania.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.potion.StoneEffectPotionEffect;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class StonethawFoodEatenProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure StonethawFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).removePotionEffect(StoneEffectPotionEffect.potion);
		}
	}
}
