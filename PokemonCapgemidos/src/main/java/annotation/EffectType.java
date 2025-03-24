package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gson.JsonDeserializer;

import effect.Effect;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
public @interface EffectType {
    String name();
    Class<? extends JsonDeserializer<? extends Effect>> deserializer();
}
