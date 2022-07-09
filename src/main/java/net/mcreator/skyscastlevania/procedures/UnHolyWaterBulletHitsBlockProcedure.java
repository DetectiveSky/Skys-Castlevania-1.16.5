package net.mcreator.skyscastlevania.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.skyscastlevania.SkysCastlevaniaModVariables;
import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class UnHolyWaterBulletHitsBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency world for procedure UnHolyWaterBulletHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure UnHolyWaterBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure UnHolyWaterBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure UnHolyWaterBulletHitsBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure UnHolyWaterBulletHitsBlock!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure UnHolyWaterBulletHitsBlock!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		if (Blocks.SOUL_SOIL == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock()
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == net.minecraft.block.material.Material.AIR
						|| !world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))).isSolid())) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, (z - 1)), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"playsound minecraft:block.glass.break ambient @p ~ ~ ~");
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.SOUL_FIRE.getDefaultState(), 3);
		} else if (Blocks.SOUL_SAND == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()
				&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))))
						.getMaterial() == net.minecraft.block.material.Material.AIR
						|| !world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))).isSolid())) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, (y + 1), (z - 1)), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"playsound minecraft:block.glass.break ambient @p ~ ~ ~");
			}
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), Blocks.SOUL_FIRE.getDefaultState(), 3);
		} else {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, (z - 1)), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"playsound minecraft:block.glass.break ambient @p ~ ~ ~");
			}
		}
		if (!imediatesourceentity.getPersistentData().getBoolean("wasFree")) {
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
