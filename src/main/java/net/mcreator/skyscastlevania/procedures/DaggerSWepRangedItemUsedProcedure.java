package net.mcreator.skyscastlevania.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.item.ThousandBladesItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class DaggerSWepRangedItemUsedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure DaggerSWepRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!(ThousandBladesItem.block == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem())) {
			{
				double _setval = ((entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive + 1);
				entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.playerSubWeaponsActive = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
