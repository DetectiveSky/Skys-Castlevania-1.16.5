package net.mcreator.skyscastlevania.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.block.PetrifiedBlockBlock;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class StoneEffectEffectStartedappliedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure StoneEffectEffectStartedapplied!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure StoneEffectEffectStartedapplied!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure StoneEffectEffectStartedapplied!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure StoneEffectEffectStartedapplied!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure StoneEffectEffectStartedapplied!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("prevHealth", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1));
		world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), PetrifiedBlockBlock.block.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), PetrifiedBlockBlock.block.getDefaultState(), 3);
	}
}
