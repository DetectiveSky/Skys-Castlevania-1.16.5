package net.mcreator.skyscastlevania.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.item.ChaosRingItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class SWepIsFreeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure SWepIsFree!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure SWepIsFree!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		if (!entity.getPersistentData().getBoolean("freeSet")
				&& ChaosRingItem.block == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem()) {
			imediatesourceentity.getPersistentData().putBoolean("freeSet", (true));
			imediatesourceentity.getPersistentData().putBoolean("wasFree", (true));
		} else if (!entity.getPersistentData().getBoolean("freeSet")) {
			imediatesourceentity.getPersistentData().putBoolean("freeSet", (true));
			imediatesourceentity.getPersistentData().putBoolean("wasFree", (false));
		} else {
			imediatesourceentity.getPersistentData().putBoolean("freeSet", (false));
			imediatesourceentity.getPersistentData().putBoolean("wasFree", (true));
		}
	}
}
