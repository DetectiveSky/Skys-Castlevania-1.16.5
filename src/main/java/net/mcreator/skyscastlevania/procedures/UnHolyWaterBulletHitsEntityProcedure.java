package net.mcreator.skyscastlevania.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.skyscastlevania.potion.ControlDisabledPotionEffect;
import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class UnHolyWaterBulletHitsEntityProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure UnHolyWaterBulletHitsEntity!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure UnHolyWaterBulletHitsEntity!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure UnHolyWaterBulletHitsEntity!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure UnHolyWaterBulletHitsEntity!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure UnHolyWaterBulletHitsEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency sourceentity for procedure UnHolyWaterBulletHitsEntity!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double multiplier = 0;
		multiplier = 1;
		if (world instanceof ServerWorld) {
			((World) world).getServer().getCommandManager().handleCommand(
					new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
							new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
					"playsound minecraft:block.glass.break ambient @p ~ ~ ~");
		}
		entity.setFire((int) 5);
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(ControlDisabledPotionEffect.potion, (int) 100, (int) 255, (false), (false)));
		if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_fire")).contains(entity.getType())) {
			multiplier = (multiplier / 2);
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(ControlDisabledPotionEffect.potion);
			}
			entity.extinguish();
		} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_fire")).contains(entity.getType())) {
			multiplier = (multiplier * 2);
		}
		if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_dark")).contains(entity.getType())) {
			DarkDamageEffectProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
							new AbstractMap.SimpleEntry<>("z", z))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			multiplier = (multiplier / 2);
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(Effects.SLOWNESS);
			}
		} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_dark")).contains(entity.getType())) {
			DarkDamageEffectProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
							new AbstractMap.SimpleEntry<>("z", z))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			multiplier = (multiplier * 2);
		}
		entity.attackEntityFrom(DamageSource.GENERIC, (float) (3 * multiplier));
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
