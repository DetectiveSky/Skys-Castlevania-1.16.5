package net.mcreator.skyscastlevania.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.potion.CurseEffectPotionEffect;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class ProjectileAttacksProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingAttackEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				Entity imediatesourceentity = event.getSource().getImmediateSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				double amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("amount", amount);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("imediatesourceentity", imediatesourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure ProjectileAttacks!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure ProjectileAttacks!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure ProjectileAttacks!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure ProjectileAttacks!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure ProjectileAttacks!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure ProjectileAttacks!");
			return;
		}
		if (dependencies.get("amount") == null) {
			if (!dependencies.containsKey("amount"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency amount for procedure ProjectileAttacks!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		double amount = dependencies.get("amount") instanceof Integer ? (int) dependencies.get("amount") : (double) dependencies.get("amount");
		double multiplier = 0;
		if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/castlevania"))
				.contains(imediatesourceentity.getType())) {
			multiplier = 2;
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/blunt")).contains(imediatesourceentity.getType())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_blunt")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_blunt")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/pointy")).contains(imediatesourceentity.getType())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_pointy")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_pointy")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/fire")).contains(imediatesourceentity.getType())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_fire")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_fire")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/ice")).contains(imediatesourceentity.getType())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_ice")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_ice")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/poison")).contains(imediatesourceentity.getType())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_poison")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, (int) 200, (int) 1, (false), (true)));
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_poison")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/curse")).contains(imediatesourceentity.getType())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_curse")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(CurseEffectPotionEffect.potion, (int) 200, (int) 1, (false), (true)));
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_curse")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:projectile/dark")).contains(imediatesourceentity.getType())) {
				DarkDamageEffectProcedure.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", (y + 1)), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_dark")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/resist_dark")).contains(entity.getType())) {
					multiplier = (multiplier / 2);
				}
			}
			entity.attackEntityFrom(DamageSource.GENERIC, (float) ((multiplier - 1) * amount));
		}
	}
}
