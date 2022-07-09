package net.mcreator.skyscastlevania.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class CrossSWepBulletHitsPlayerProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure CrossSWepBulletHitsPlayer!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure CrossSWepBulletHitsPlayer!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency sourceentity for procedure CrossSWepBulletHitsPlayer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (imediatesourceentity.getPersistentData().getBoolean("return") && entity == sourceentity) {
			if (!imediatesourceentity.world.isRemote())
				imediatesourceentity.remove();
			if (!imediatesourceentity.getPersistentData().getBoolean("wasFree")) {
				{
					double _setval = ((sourceentity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive - 1);
					sourceentity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.playerSubWeaponsActive = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
			}
		}
	}
}
