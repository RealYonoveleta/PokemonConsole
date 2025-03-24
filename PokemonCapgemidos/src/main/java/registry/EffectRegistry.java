package registry;

import effect.Effect;

public class EffectRegistry extends AbstractRegistry<Effect> {

	private static final EffectRegistry INSTANCE = new EffectRegistry();

	private EffectRegistry() {
		super();
	}

	public static EffectRegistry getInstance() {
		return INSTANCE;
	}
	

}
