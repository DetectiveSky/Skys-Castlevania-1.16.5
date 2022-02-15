package net.mcreator.skyscastlevania.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.skyscastlevania.item.HeartItem;
import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Random;
import java.util.Map;

public class ObsidianKnifeOnPlayerStoppedUsingProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure ObsidianKnifeOnPlayerStoppedUsing!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure ObsidianKnifeOnPlayerStoppedUsing!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency itemstack for procedure ObsidianKnifeOnPlayerStoppedUsing!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (!(entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SkysCastlevaniaModVariables.PlayerVariables())).itemCrashActive) {
			{
				boolean _setval = (true);
				entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.itemCrashActive = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			entity.attackEntityFrom(DamageSource.MAGIC, (float) 2);
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(HeartItem.block);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
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
				{
					ItemStack _ist = itemstack;
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
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
					{
						boolean _setval = (false);
						entity.getCapability(SkysCastlevaniaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.itemCrashActive = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 20);
		}
	}
}
