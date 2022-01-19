package net.mcreator.skyscastlevania.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class GoldEffectEffectExpiresProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure GoldEffectEffectExpires!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure GoldEffectEffectExpires!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure GoldEffectEffectExpires!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure GoldEffectEffectExpires!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), false);
		world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), false);
	}
}
