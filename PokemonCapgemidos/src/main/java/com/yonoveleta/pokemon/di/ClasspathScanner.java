package com.yonoveleta.pokemon.di;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClasspathScanner {

	private AnnotationRegistry registry = AnnotationRegistry.getInstance();

	public void scanAndRegister() {
		for (Class<? extends Annotation> annotation : registry.getClassAnnotationsToScan()) {
			scanForClassesWithAnnotation(annotation);
		}
	}

	// Scan the classpath for classes annotated with the given annotation
	public Set<Class<?>> scanForClassesWithAnnotation(Class<? extends Annotation> annotation) {
		Set<Class<?>> annotatedClasses = new HashSet<>();

		// Get the classpath from the system properties (this includes both JAR files
		// and directories)
		String classpath = System.getProperty("java.class.path");

		// Iterate over the classpath entries (directories or JAR files)
		for (String path : classpath.split(File.pathSeparator)) {
			try {
				// If it's a directory, scan for classes directly
				File file = new File(path);
				if (file.isDirectory()) {
					// Here we scan the directory for `.class` files
					scanDirectory(file, annotation, "", annotatedClasses);
				}
				// Optionally, handle JAR files here
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return annotatedClasses;
	}

	// Scan a directory recursively and check for classes with the given annotation
	private void scanDirectory(File directory, Class<? extends Annotation> annotation, String packageName,
			Set<Class<?>> annotatedClasses) {
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				scanDirectory(file, annotation, packageName + file.getName() + ".", annotatedClasses);
			} else if (file.getName().endsWith(".class")) {
				String className = packageName + file.getName().substring(0, file.getName().length() - 6);
				try {
					Class<?> clazz = Class.forName(className);
					if (clazz.isAnnotationPresent(annotation)) {
						annotatedClasses.add(clazz);
						registry.register(clazz, annotation);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Class<?>> scanForAnnotatedClasses(String basePackage, Class<? extends Annotation> annotation)
			throws IOException, ClassNotFoundException {
		// Convert package name to file path
		String basePackagePath = basePackage.replace('.', '/');
		URL resource = ClassLoader.getSystemClassLoader().getResource(basePackagePath);
		File directory = new File(resource.getFile());
		List<Class<?>> annotatedClasses = new ArrayList<>();

		// Recursively scan the package and its sub-packages
		scanDirectoryForAnnotatedClasses(directory, basePackage, annotation, annotatedClasses);

		return annotatedClasses;
	}

	private void scanDirectoryForAnnotatedClasses(File directory, String basePackage,
			Class<? extends Annotation> annotation, List<Class<?>> annotatedClasses) throws ClassNotFoundException {
		// Get all files in the directory (or sub-directories)
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					// Recurse into sub-package
					scanDirectoryForAnnotatedClasses(file, basePackage + "." + file.getName(), annotation,
							annotatedClasses);
				} else if (file.getName().endsWith(".class")) {
					// Load class and check for annotation
					String className = basePackage + "." + file.getName().substring(0, file.getName().length() - 6); // Remove
																														// .class
																														// extension
					Class<?> clazz = Class.forName(className);
					if (clazz.isAnnotationPresent(annotation)) {
						annotatedClasses.add(clazz);
						System.out.println("Found annotated class: " + clazz.getName());
						registry.register(clazz, annotation);
					}
				}
			}
		}
	}

	// Method to scan and register annotated fields
	public void scanAndRegisterAnnotatedFields(Class<? extends Annotation> annotation) {
		// Step 1: Iterate over the registered classes for a specific annotation
		for (Class<?> clazz : registry.getAllAnnotatedClasses()) {
			// Step 2: For each class, scan its fields for the specified annotation
			// (Injectable in this case)
			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				// Step 3: If the field is annotated with the desired annotation, process it
				if (field.isAnnotationPresent(annotation)) {
					// Process the annotated field (e.g., register it for dependency injection)
					System.out.println("Found annotated field: " + field.getName() + " in class " + clazz.getName());
					registry.register(field, annotation);
				}
			}
		}
	}

	// Access the class annotation registry
	public AnnotationRegistry getRegistry() {
		return registry;
	}
}