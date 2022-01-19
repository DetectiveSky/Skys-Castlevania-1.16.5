package net.mcreator.skyscastlevania.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class IronTAxeBulletHitsEntityProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure IronTAxeBulletHitsEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency sourceentity for procedure IronTAxeBulletHitsEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double multiplier = 0;
		multiplier = 1;
		if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_sharp")).contains(entity.getType())) {
			multiplier = (multiplier * 2);
		} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_sharp")).contains(entity.getType())) {
			multiplier = (multiplier / 2);
		}
		entity.attackEntityFrom(DamageSource.GENERIC, (float) (9 * multiplier));
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
