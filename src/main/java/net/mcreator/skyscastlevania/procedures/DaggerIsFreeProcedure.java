package net.mcreator.skyscastlevania.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.item.ThousandBladesItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class DaggerIsFreeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure DaggerIsFree!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure DaggerIsFree!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		if (!entity.getPersistentData().getBoolean("freeSet")
				&& ThousandBladesItem.block == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
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
