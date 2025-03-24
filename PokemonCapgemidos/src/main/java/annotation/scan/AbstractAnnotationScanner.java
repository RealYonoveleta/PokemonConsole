package annotation.scan;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public abstract class AbstractAnnotationScanner<T extends Annotation> implements AnnotationScanner<T> {

	protected Class<? extends Annotation> annotationClass;

	protected AbstractAnnotationScanner(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	@Override
	public void scan() {
		Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("")));

		Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotationClass);

		// Register each annotated class
		for (Class<?> clazz : annotatedClasses) {
			registerAnnotation(clazz);
		}

	}

	protected abstract void registerAnnotation(Class<?> clazz);

}
