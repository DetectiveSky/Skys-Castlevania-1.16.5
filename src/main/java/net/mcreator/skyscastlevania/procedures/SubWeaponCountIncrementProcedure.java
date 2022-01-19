package net.mcreator.skyscastlevania.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class SubWeaponCountIncrementProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure SubWeaponCountIncrement!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (1 + (entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive);
			entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.playerSubWeaponsActive = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
