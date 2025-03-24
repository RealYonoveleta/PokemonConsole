package annotation.scan;

import com.google.gson.JsonDeserializer;

import annotation.StatusType;
import registry.StatusRegistry;
import status.Status;

public class StatusTypeScanner extends AbstractAnnotationScanner<StatusType> {

	public StatusTypeScanner() {
		super(StatusType.class);
	}

	@Override
	protected void registerAnnotation(Class<?> clazz) {
		StatusType annotation = clazz.getAnnotation(StatusType.class);
		try {
			Class<? extends JsonDeserializer<? extends Status>> deserializerClass = annotation.deserializer();
			JsonDeserializer<? extends Status> deserializer = deserializerClass.getDeclaredConstructor().newInstance();
			
			if (!Status.class.isAssignableFrom(clazz)) {
			    throw new IllegalArgumentException(clazz.getName() + " is not a subclass of Status");
			}
			@SuppressWarnings("unchecked")
			Class<? extends Status> statusClass = (Class<? extends Status>) clazz;
			
			StatusRegistry.getInstance().register(annotation.name(), statusClass, deserializer);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Failed to instantiate deserializer for " + clazz.getName(), e);
		}
	}

}
