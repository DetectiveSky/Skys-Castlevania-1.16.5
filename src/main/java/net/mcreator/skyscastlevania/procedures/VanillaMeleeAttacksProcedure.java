package net.mcreator.skyscastlevania.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.world.VanillaWeaponTypingGameRule;
import net.mcreator.skyscastlevania.potion.StoneEffectPotionEffect;
import net.mcreator.skyscastlevania.potion.GoldEffectPotionEffect;
import net.mcreator.skyscastlevania.potion.CurseEffectPotionEffect;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class VanillaMeleeAttacksProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingHurtEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
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
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure VanillaMeleeAttacks!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure VanillaMeleeAttacks!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure VanillaMeleeAttacks!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure VanillaMeleeAttacks!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure VanillaMeleeAttacks!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency sourceentity for procedure VanillaMeleeAttacks!");
			return;
		}
		if (dependencies.get("amount") == null) {
			if (!dependencies.containsKey("amount"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency amount for procedure VanillaMeleeAttacks!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double amount = dependencies.get("amount") instanceof Integer ? (int) dependencies.get("amount") : (double) dependencies.get("amount");
		double multiplier = 0;
		if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/vanilla_melee"))
				.contains(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())
				&& world.getWorldInfo().getGameRulesInstance().getBoolean(VanillaWeaponTypingGameRule.gamerule)) {
			multiplier = 1;
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/sharp")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_sharp")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/blunt")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_blunt")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/pointy")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_pointy")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/fire")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_fire")).contains(entity.getType())) {
					FireDamageEffectProcedure.executeProcedure(Stream
							.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
									new AbstractMap.SimpleEntry<>("y", (y + 1)), new AbstractMap.SimpleEntry<>("z", z))
							.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/ice")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_ice")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/lightning")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_lightning")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/holy")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_holy")).contains(entity.getType())) {
					HolyDamageEffectProcedure.executeProcedure(Stream
							.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
									new AbstractMap.SimpleEntry<>("y", (y + 1)), new AbstractMap.SimpleEntry<>("z", z))
							.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/dark")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_dark")).contains(entity.getType())) {
					DarkDamageEffectProcedure.executeProcedure(Stream
							.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
									new AbstractMap.SimpleEntry<>("y", (y + 1)), new AbstractMap.SimpleEntry<>("z", z))
							.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
					multiplier = (multiplier * 2);
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/poison")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_poison")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, (int) 100, (int) 1, (false), (true)));
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/curse")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_curse")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(CurseEffectPotionEffect.potion, (int) 100, (int) 1, (false), (true)));
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/stone")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_stone")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(StoneEffectPotionEffect.potion, (int) 100, (int) 1, (false), (true)));
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/gold")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_gold")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(GoldEffectPotionEffect.potion, (int) 100, (int) 1, (false), (true)));
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:weapon/wind")).contains(
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())) {
				if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mob/weak_wind")).contains(entity.getType())) {
					multiplier = (multiplier * 2);
				}
			}
			entity.attackEntityFrom(DamageSource.GENERIC, (float) ((multiplier - 1) * amount));
		}
	}
}
