package annotation.scan;

import com.google.gson.JsonDeserializer;

import annotation.EffectType;
import effect.Effect;
import registry.EffectRegistry;

public class EffectTypeScanner extends AbstractAnnotationScanner<EffectType>{

	public EffectTypeScanner() {
		super(EffectType.class);
	}

	@Override
	protected void registerAnnotation(Class<?> clazz) {
		EffectType annotation = clazz.getAnnotation(EffectType.class);
		try {
			Class<? extends JsonDeserializer<? extends Effect>> deserializerClass = annotation.deserializer();
			JsonDeserializer<? extends Effect> deserializer = deserializerClass.getDeclaredConstructor().newInstance();
			
			if (!Effect.class.isAssignableFrom(clazz)) {
			    throw new IllegalArgumentException(clazz.getName() + " is not a subclass of Effect");
			}
			@SuppressWarnings("unchecked")
			Class<? extends Effect> effectClass = (Class<? extends Effect>) clazz;
			
			EffectRegistry.getInstance().register(annotation.name(), effectClass, deserializer);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Failed to instantiate deserializer for " + clazz.getName(), e);
		}
	}

}
