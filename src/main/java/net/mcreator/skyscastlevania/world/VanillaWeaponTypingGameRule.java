package net.mcreator.skyscastlevania.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.skyscastlevania.SkysCastlevaniaModElements;

import java.lang.reflect.Method;

@SkysCastlevaniaModElements.ModElement.Tag
public class VanillaWeaponTypingGameRule extends SkysCastlevaniaModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("vanillaWeaponTyping", GameRules.Category.PLAYER,
			create(false));

	public VanillaWeaponTypingGameRule(SkysCastlevaniaModElements instance) {
		super(instance, 223);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
