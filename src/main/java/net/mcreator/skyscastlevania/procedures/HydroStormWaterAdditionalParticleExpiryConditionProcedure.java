package net.mcreator.skyscastlevania.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;

import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class HydroStormWaterAdditionalParticleExpiryConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure HydroStormWaterAdditionalParticleExpiryCondition!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure HydroStormWaterAdditionalParticleExpiryCondition!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure HydroStormWaterAdditionalParticleExpiryCondition!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure HydroStormWaterAdditionalParticleExpiryCondition!");
			return false;
		}
		if (dependencies.get("onGround") == null) {
			if (!dependencies.containsKey("onGround"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency onGround for procedure HydroStormWaterAdditionalParticleExpiryCondition!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		boolean onGround = (boolean) dependencies.get("onGround");
		if (onGround) {
			world.addParticle(ParticleTypes.RAIN, x, y, z, 0, 0, 0);
			return true;
		}
		return false;
	}
}
