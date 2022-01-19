package net.mcreator.skyscastlevania.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class CrossSWepWhileBulletFlyingTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure CrossSWepWhileBulletFlyingTick!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure CrossSWepWhileBulletFlyingTick!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure CrossSWepWhileBulletFlyingTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		imediatesourceentity.setNoGravity((true));
		if (!imediatesourceentity.getPersistentData().getBoolean("return") && 10 <= Math
				.sqrt(Math.pow(entity.getPosX() - imediatesourceentity.getPosX(), 2) + Math.pow(entity.getPosY() - imediatesourceentity.getPosY(), 2)
						+ Math.pow(entity.getPosZ() - imediatesourceentity.getPosZ(), 2))) {
			imediatesourceentity.setMotion(((-1) * imediatesourceentity.getMotion().getX()), ((-1) * imediatesourceentity.getMotion().getY()),
					((-1) * imediatesourceentity.getMotion().getZ()));
			imediatesourceentity.getPersistentData().putBoolean("return", (true));
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
					imediatesourceentity.getPersistentData().putBoolean("return", (true));
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 0);
		} else if (imediatesourceentity.getPersistentData().getBoolean("return") && 20 <= Math
				.sqrt(Math.pow(entity.getPosX() - imediatesourceentity.getPosX(), 2) + Math.pow(entity.getPosY() - imediatesourceentity.getPosY(), 2)
						+ Math.pow(entity.getPosZ() - imediatesourceentity.getPosZ(), 2))) {
			if (!imediatesourceentity.world.isRemote())
				imediatesourceentity.remove();
			{
				double _setval = ((entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).playerSubWeaponsActive - 1);
				entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.playerSubWeaponsActive = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
