package net.mcreator.skyscastlevania.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;

import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class MoonCardRightClickedInAirProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure MoonCardRightClickedInAir!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency itemstack for procedure MoonCardRightClickedInAir!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (world.getWorldInfo().getDayTime() % 24000 < 12000) {
			(itemstack).shrink((int) 1);
			while (world.getWorldInfo().getDayTime() % 24000 != 12000) {
				if (world instanceof ServerWorld)
					((ServerWorld) world).setDayTime((int) (world.getWorldInfo().getDayTime() + 1));
			}
		}
	}
}
