package com.yonoveleta.pokemon.di;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yonoveleta.pokemon.di.annotation.PokemonApplication;
import com.yonoveleta.pokemon.io.log.CentralLogger;

public class AnnotationRegistry {

	// The single instance of the AnnotationRegistry
	private static AnnotationRegistry instance;

	// Private constructor to prevent instantiation
	private AnnotationRegistry() {
	}

	public static AnnotationRegistry getInstance() {
		if (instance == null) {
			instance = new AnnotationRegistry();
		}
		return instance;
	}


	// Map to store annotations and their associated classes
	private static Map<Class<? extends Annotation>, List<Class<?>>> annotationClassMap = new HashMap<>();

	// Map to store annotations and their associated fields
	private static Map<Class<? extends Annotation>, List<Field>> annotationFieldMap = new HashMap<>();

	// Registers a class for a given annotation
	public static void register(Class<?> clazz, Class<? extends Annotation> annotation) {
		List<Class<?>> registeredClasses = annotationClassMap.computeIfAbsent(annotation, _ -> new ArrayList<>());

		// Check if the class is already registered
		if (!registeredClasses.contains(clazz)) {
			registeredClasses.add(clazz);
			CentralLogger.logInfo("Class %s registered for annotation %s", clazz, annotation.getSimpleName());
		}
	}

	// Registers a field for a given annotation
	public static void register(Field field, Class<? extends Annotation> annotation) {
		List<Field> registeredFields = annotationFieldMap.computeIfAbsent(annotation, _ -> new ArrayList<>());

		// Check if the field is already registered
		if (!registeredFields.contains(field)) {
			registeredFields.add(field);
			CentralLogger.logInfo("Field %s registered for annotation %s", field, annotation.getSimpleName());
		}
	}

	// Retrieves classes annotated with a specific annotation
	public List<Class<?>> getClassesForAnnotation(Class<? extends Annotation> annotation) {
		return annotationClassMap.getOrDefault(annotation, new ArrayList<>());
	}

	// Method to get all annotated classes (all annotations)
	public List<Class<?>> getAllAnnotatedClasses() {
		List<Class<?>> allClasses = new ArrayList<>();
		for (List<Class<?>> classes : annotationClassMap.values()) {
			allClasses.addAll(classes);
		}
		return allClasses;
	}

	// Method to get all annotated classes (all annotations)
	public List<Field> getAllAnnotatedFields() {
		List<Field> allFields = new ArrayList<>();
		for (List<Field> fields : annotationFieldMap.values()) {
			allFields.addAll(fields);
		}
		return allFields;
	}

	// Retrieves all annotations with their classes
	public Map<Class<? extends Annotation>, List<Class<?>>> getAllRegisteredAnnotations() {
		return annotationClassMap;
	}

	// Check if @PokemonApplication is registered
	public Class<?> getPokemonApplicationClass() {
		List<Class<?>> classes = getClassesForAnnotation(PokemonApplication.class);
		if (classes.isEmpty()) {
			throw new RuntimeException("No class annotated with @PokemonApplication found.");
		}

		if (classes.size() > 1) {
			CentralLogger.logError("More than one @PokemonApplication found", null);
		}

		Class<?> pokemonAppClass = classes.get(0);
		CentralLogger.logInfo("Detected PokemonApplication at %s", pokemonAppClass);
		return pokemonAppClass;
	}

}
