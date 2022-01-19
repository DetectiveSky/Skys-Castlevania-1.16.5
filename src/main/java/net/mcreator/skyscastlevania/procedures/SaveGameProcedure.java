package net.mcreator.skyscastlevania.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.skyscastlevania.SkysCastlevaniaMod;

import java.util.Map;

public class SaveGameProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency x for procedure SaveGame!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency y for procedure SaveGame!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency z for procedure SaveGame!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkysCastlevaniaMod.LOGGER.warn("Failed to load dependency entity for procedure SaveGame!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof ServerPlayerEntity)
			((ServerPlayerEntity) entity).func_242111_a(((ServerPlayerEntity) entity).world.getDimensionKey(),
					new BlockPos((int) x, (int) y, (int) z), 0, true, false);
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 20, (int) 6, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SATURATION, (int) 20, (int) 1, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).clearActivePotions();
		entity.extinguish();
		entity.setNoGravity((false));
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Kyrie eleison..."), (true));
		}
	}
}
