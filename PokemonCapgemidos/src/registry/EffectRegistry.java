package registry;

import java.util.HashMap;
import java.util.Map;

import effect.Effect;
import effect.HealingEffect;
import effect.StatusEffect;

public class EffectRegistry {

	private static final Map<String, Class<? extends Effect>> effectMap = new HashMap<>();
	
	static {
		registerEffect("heal", HealingEffect.class);
		registerEffect("status", StatusEffect.class);
	}

	public static void registerEffect(String name, Class<? extends Effect> effectClass) {
		effectMap.put(name, effectClass);
	}

	public static Class<? extends Effect> getEffectClass(String name) {
		return effectMap.get(name);
	}
	
}
