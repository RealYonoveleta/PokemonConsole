package com.yonoveleta.pokemon.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.yonoveleta.pokemon.io.log.CentralLogger;

public class DependencyInjector {

	private static final Map<Class<?>, Object> instanceMap = new HashMap<>();

	// Step 1: Create instances of all registered classes
	public static void initializeInstances(AnnotationRegistry registry) {
		for (Class<?> clazz : registry.getAllAnnotatedClasses()) {
			instanceMap.put(clazz, getOrCreateInstance(clazz));
			CentralLogger.logInfo("%s initialized", clazz);
		}
	}

	// Step 2: Inject dependencies into fields
	public static void injectDependencies(AnnotationRegistry registry) {
		for (Field field : registry.getAllAnnotatedFields()) {
			field.setAccessible(true);
			try {
				Class<?> declaringClass = field.getDeclaringClass();
				field.set(instanceMap.get(declaringClass), instanceMap.get(field.getType()));
				CentralLogger.logInfo("%s injected into %s", field.getType().getSimpleName(),
						declaringClass.getSimpleName());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				CentralLogger.logError("Error during dependency injection", e);
			} 
		}
	}

	// Tries to obtain an instance using getInstance(), or falls back to constructor
	private static Object getOrCreateInstance(Class<?> clazz) {
		try {
			// Check if the class has a getInstance() method
			Method getInstanceMethod = null;
			try {
				getInstanceMethod = clazz.getMethod("getInstance");
			} catch (NoSuchMethodException ignored) {
			}

			if (getInstanceMethod != null && getInstanceMethod.getReturnType().equals(clazz)) {
				return getInstanceMethod.invoke(null);
			}

			// Fall back to constructor if getInstance() is not present
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true); // Allow private constructors
			return constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Failed to create instance for " + clazz.getName(), e);
		}
	}

	public static Map<Class<?>, Object> getInstaceMap() {
		return instanceMap;
	}

	public static Object getInstanceOfClass(Class<?> clazz) {
		return instanceMap.get(clazz);
	}

}