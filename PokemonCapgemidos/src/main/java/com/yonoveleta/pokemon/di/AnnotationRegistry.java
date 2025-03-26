package com.yonoveleta.pokemon.di;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yonoveleta.pokemon.di.annotation.Factory;
import com.yonoveleta.pokemon.di.annotation.Inject;
import com.yonoveleta.pokemon.di.annotation.Logic;
import com.yonoveleta.pokemon.di.annotation.PokemonApplication;

public class AnnotationRegistry {

	// The single instance of the AnnotationRegistry
	private static AnnotationRegistry instance;

	// Private constructor to prevent instantiation
	private AnnotationRegistry() {
		addClassAnnotationToScan(PokemonApplication.class);
		addClassAnnotationToScan(Logic.class);
		addClassAnnotationToScan(Factory.class);

		addFieldAnnotationToScan(Inject.class);
	}

	public static AnnotationRegistry getInstance() {
		if (instance == null) {
			instance = new AnnotationRegistry();
		}
		return instance;
	}

	// List of annotations to scan for
	private final List<Class<? extends Annotation>> classAnnotationsToScan = new ArrayList<>();

	// List of field annotations to scan for
	private final List<Class<? extends Annotation>> fieldAnnotationsToScan = new ArrayList<>();

	// Map to store annotations and their associated classes
	private Map<Class<? extends Annotation>, List<Class<?>>> annotationClassMap = new HashMap<>();

	// Map to store annotations and their associated fields
	private Map<Class<? extends Annotation>, List<Field>> annotationFieldMap = new HashMap<>();

	// Registers a class for a given annotation
	public void register(Class<?> clazz, Class<? extends Annotation> annotation) {
		List<Class<?>> registeredClasses = annotationClassMap.computeIfAbsent(annotation, _ -> new ArrayList<>());

		// Check if the class is already registered
		if (!registeredClasses.contains(clazz)) {
			registeredClasses.add(clazz);
		}
	}

	// Registers a field for a given annotation
	public void register(Field field, Class<? extends Annotation> annotation) {
		List<Field> registeredFields = annotationFieldMap.computeIfAbsent(annotation, _ -> new ArrayList<>());

		// Check if the field is already registered
		if (!registeredFields.contains(field)) {
			registeredFields.add(field);
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
		return classes.get(0); // Assume there's only one, or handle multiple cases
	}

	// Method to get the list of class annotations being scanned for
	public List<Class<? extends Annotation>> getClassAnnotationsToScan() {
		return classAnnotationsToScan;
	}

	// Method to get the list of class annotations being scanned for
	public List<Class<? extends Annotation>> getFieldAnnotationsToScan() {
		return fieldAnnotationsToScan;
	}

	// Method to add more class annotations to be scanned
	public void addClassAnnotationToScan(Class<? extends Annotation> annotation) {
		classAnnotationsToScan.add(annotation);
	}

	// Method to add more field annotations to be scanned
	public void addFieldAnnotationToScan(Class<? extends Annotation> annotation) {
		fieldAnnotationsToScan.add(annotation);
	}

}
