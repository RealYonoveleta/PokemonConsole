package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gson.JsonDeserializer;

import status.Status;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
public @interface StatusType {
    String name();
    Class<? extends JsonDeserializer<Status>> deserializer();
}
