package net.mcreator.skyscastlevania.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.item.DebugHeartItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Random;
import java.util.Map;

public class DebugHeartRightClickedInAirProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure DebugHeartRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (DebugHeartItem.block == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem()) {
			{
				ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
		}
	}
}
