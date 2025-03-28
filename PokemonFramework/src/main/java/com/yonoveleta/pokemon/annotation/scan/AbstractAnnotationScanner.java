package com.yonoveleta.pokemon.annotation.scan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.yonoveleta.pokemon.di.AnnotationRegistry;
import com.yonoveleta.pokemon.io.log.CentralLogger;

public abstract class AbstractAnnotationScanner<T extends Annotation> implements AnnotationScanner<T> {

	protected Class<? extends Annotation> annotationClass;
	protected Scanners scannerType;
	
	protected AbstractAnnotationScanner(Class<? extends Annotation> annotationClass, Scanners scannerType) {
		this.annotationClass = annotationClass;
		this.scannerType = scannerType;
	}
	
	@Override
	public void scan() {
		Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .addScanners(scannerType)
        );
		
		if(scannerType == Scanners.TypesAnnotated) {
			scanClasses(reflections);
		} 
		else if(scannerType == Scanners.FieldsAnnotated) {
			scanFields(reflections);
		}
	}

	private void scanClasses(Reflections reflections) {
        // Find all classes annotated with the given annotation
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotationClass);

        // Register each annotated class
        for (Class<?> clazz : annotatedClasses) {
        	CentralLogger.logInfo("Found class %s annotated with %s", clazz, annotationClass.getSimpleName());
            registerAnnotation(clazz);
        }
    }
	
	private void scanFields(Reflections reflections) {
        // Find all fields annotated with the given annotation
        Set<Field> annotatedFields = reflections.getFieldsAnnotatedWith(annotationClass);

        // Register each annotated class
        for (Field field : annotatedFields) {
        	CentralLogger.logInfo("Found field %s annotated with %s", field, annotationClass.getSimpleName());
            registerAnnotation(field);
        }
    }
	
	protected void registerAnnotation(Class<?> clazz) {
		AnnotationRegistry.register(clazz, annotationClass);
	};
	
	protected void registerAnnotation(Field field) {
		AnnotationRegistry.register(field, annotationClass);
	};


}
