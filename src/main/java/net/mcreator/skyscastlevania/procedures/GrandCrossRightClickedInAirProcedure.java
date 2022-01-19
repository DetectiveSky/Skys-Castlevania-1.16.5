package net.mcreator.skyscastlevania.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.skyscastlevania.potion.ControlDisabledPotionEffect;
import net.mcreator.skyscastlevania.particle.HolyDamageParticleParticle;
import net.mcreator.skyscastlevania.item.BigHeartItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.AbstractMap;

public class GrandCrossRightClickedInAirProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure GrandCrossRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure GrandCrossRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure GrandCrossRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure GrandCrossRightClickedInAir!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure GrandCrossRightClickedInAir!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency itemstack for procedure GrandCrossRightClickedInAir!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		boolean canDo = false;
		double numHearts = 0;
		double loopTrack = 0;
		canDo = (false);
		if (!(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity))) {
			if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(BigHeartItem.block)) : false) {
				numHearts = 0;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (BigHeartItem.block == itemstackiterator.getItem()) {
								numHearts = (numHearts + (itemstackiterator).getCount());
							}
							if (3 <= numHearts) {
								canDo = (true);
								{
									ItemStack _ist = itemstack;
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
								break;
							}
						}
					}
				}
			}
		} else {
			canDo = (true);
		}
		if (canDo && !(entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).itemCrashActive) {
			{
				boolean _setval = (true);
				entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.itemCrashActive = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("GRAND CROSS!"), (false));
			}
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.disableDamage = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity)
						.addPotionEffect(new EffectInstance(ControlDisabledPotionEffect.potion, (int) 100, (int) 255, (false), (false)));
			entity.setNoGravity((true));
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(x, (y + 1), z);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 1), z, _ent.rotationYaw, _ent.rotationPitch,
							Collections.emptySet());
				}
			}
			if (world.isRemote()) {
				Minecraft.getInstance().gameRenderer.displayItemActivation(itemstack);
			}
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("skys_castlevania:animeshine")),
						SoundCategory.PLAYERS, (float) 0.5, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("skys_castlevania:animeshine")),
						SoundCategory.PLAYERS, (float) 0.5, (float) 1, false);
			}
			loopTrack = 0;
			for (int index0 = 0; index0 < (int) (5); index0++) {
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						{
							List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
									new AxisAlignedBB(x - (20 / 2d), y - (20 / 2d), z - (20 / 2d), x + (20 / 2d), y + (20 / 2d), z + (20 / 2d)), null)
									.stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
										}
									}.compareDistOf(x, y, z)).collect(Collectors.toList());
							for (Entity entityiterator : _entfound) {
								if (!(entityiterator == entity)
										&& (entityiterator instanceof MonsterEntity || entityiterator instanceof ServerPlayerEntity)) {
									if (!(new Object() {
										boolean check(Entity _entity) {
											if (_entity instanceof LivingEntity) {
												Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
												for (EffectInstance effect : effects) {
													if (effect.getPotion() == Effects.SLOWNESS)
														return true;
												}
											}
											return false;
										}
									}.check(entityiterator))) {
										if (entityiterator instanceof LivingEntity)
											((LivingEntity) entityiterator)
													.addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 100, (int) 255, (false), (false)));
									}
									if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mobs/weak_holy"))
											.contains(entityiterator.getType())) {
										HolyDamageEffectProcedure.executeProcedure(Stream
												.of(new AbstractMap.SimpleEntry<>("world", world),
														new AbstractMap.SimpleEntry<>("x", (entityiterator.getPosX())),
														new AbstractMap.SimpleEntry<>("y", (entityiterator.getPosY() + 1)),
														new AbstractMap.SimpleEntry<>("z", (entityiterator.getPosZ())))
												.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
										entityiterator.attackEntityFrom(DamageSource.GENERIC, (float) 4);
									} else if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:mobs/resist_holy"))
											.contains(entityiterator.getType())) {
										HolyDamageEffectProcedure.executeProcedure(Stream
												.of(new AbstractMap.SimpleEntry<>("world", world),
														new AbstractMap.SimpleEntry<>("x", (entityiterator.getPosX())),
														new AbstractMap.SimpleEntry<>("y", (entityiterator.getPosY() + 1)),
														new AbstractMap.SimpleEntry<>("z", (entityiterator.getPosZ())))
												.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
										entityiterator.attackEntityFrom(DamageSource.GENERIC, (float) 1);
									} else {
										entityiterator.attackEntityFrom(DamageSource.GENERIC, (float) 2);
									}
								}
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) (20 * loopTrack));
				loopTrack = (loopTrack + 1);
			}
			loopTrack = 0;
			for (int index1 = 0; index1 < (int) (25); index1++) {
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						for (int index2 = 0; index2 < (int) (50); index2++) {
							world.addParticle(HolyDamageParticleParticle.particle, (x - 10 + 20 * Math.random()), y, (z - 10 + 20 * Math.random()), 0,
									0.75, 0);
						}
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")),
									SoundCategory.PLAYERS, (float) 0.5, (float) 1);
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")),
									SoundCategory.PLAYERS, (float) 0.5, (float) 1, false);
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) (4 * loopTrack));
				loopTrack = (loopTrack + 1);
			}
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					entity.setNoGravity((false));
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(ControlDisabledPotionEffect.potion);
					}
					if (entity instanceof PlayerEntity) {
						((PlayerEntity) entity).abilities.disableDamage = (new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity));
						((PlayerEntity) entity).sendPlayerAbilities();
					}
					{
						boolean _setval = (false);
						entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.itemCrashActive = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 100);
		}
	}
}
