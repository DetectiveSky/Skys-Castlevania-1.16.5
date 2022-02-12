package net.mcreator.skyscastlevania.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.potion.StoneEffectPotionEffect;
import net.mcreator.skyscastlevania.potion.ControlDisabledPotionEffect;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class StoneEffectOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure StoneEffectOnEffectActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(ControlDisabledPotionEffect.potion, (int) 2, (int) 1, (false), (false)));
		if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) < entity.getPersistentData().getDouble("prevHealth")) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(StoneEffectPotionEffect.potion);
			}
		}
		entity.getPersistentData().putDouble("prevHealth", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1));
	}
}
