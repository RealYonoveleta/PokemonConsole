package com.yonoveleta.pokemon.env;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

import com.yonoveleta.pokemon.annotation.scan.AnnotationScanner;
import com.yonoveleta.pokemon.di.AnnotationRegistry;
import com.yonoveleta.pokemon.di.ClasspathScanner;
import com.yonoveleta.pokemon.di.DependencyInjector;
import com.yonoveleta.pokemon.di.annotation.Logic;
import com.yonoveleta.pokemon.di.annotation.PokemonApplication;

public class EngineStarter {

	private static String basePackage;
	private static final String FRAMEWORK_BASE_PACKAGE = "com.yonoveleta.pokemon";

	// Method to initialize the entire engine/environment
	public static void start() {
		// Initialize ServiceLoader for dynamic loading of modules, etc.
		initializeServiceLoader();

		// Classpath scanning
		ClasspathScanner scanner = new ClasspathScanner();
		Class<?> app = getPokemonApplication(scanner);
		basePackage = getBasePackageFromPokemonApplication(app);

		scanAndRegisterClassAnnotations(scanner);
		scanAndRegisterFieldAnnotations(scanner);

		// Depencendy injection
		AnnotationRegistry registry = scanner.getRegistry();
		DependencyInjector.initializeInstances(registry);
		DependencyInjector.injectDependencies(registry);

		// Get game logic classes
		List<GameLogic> logics = getLogicInstances(registry);

		// Any other initialization logic can go here
		System.out.println("Engine initialized successfully.");

		for (GameLogic logic : logics) {
			logic.run();
		}
	}

	private static void initializeServiceLoader() {
		ServiceLoader.load(AnnotationScanner.class).forEach(AnnotationScanner::scan);
	}

	public static Class<?> getPokemonApplication(ClasspathScanner scanner) {
		// Find the class annotated with @PokemonApplication
		Set<Class<?>> pokemonAppClasses = scanner.scanForClassesWithAnnotation(PokemonApplication.class);

		if (!pokemonAppClasses.isEmpty()) {
			// Get the first class that has the @PokemonApplication annotation
			Class<?> pokemonAppClass = pokemonAppClasses.iterator().next();
			// Return the package name of the class
			return pokemonAppClass;
		} else {
			throw new RuntimeException("No class found with @PokemonApplication annotation");
		}
	}

	public static void scanAndRegisterClassAnnotations(ClasspathScanner scanner) {
		for (Class<? extends Annotation> annotation : scanner.getRegistry().getClassAnnotationsToScan()) {
			try {
				scanner.scanForAnnotatedClasses(basePackage, annotation);
				scanner.scanForAnnotatedClasses(FRAMEWORK_BASE_PACKAGE, annotation);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void scanAndRegisterFieldAnnotations(ClasspathScanner scanner) {
		for (Class<? extends Annotation> annotation : scanner.getRegistry().getFieldAnnotationsToScan()) {
			scanner.scanAndRegisterAnnotatedFields(annotation);
		}
	}

	public static String getBasePackageFromPokemonApplication(Class<?> clazz) {
		return clazz.getPackage().getName();
	}

	public static List<GameLogic> getLogicInstances(AnnotationRegistry registry) {
		List<Class<?>> logicClasses = registry.getClassesForAnnotation(Logic.class);
		List<Object> instances = new ArrayList<>();
		List<GameLogic> gameLogicInstances = new ArrayList<>();

		for (Class<?> clazz : logicClasses) {
			instances.add(DependencyInjector.getInstanceOfClass(clazz));
		}

		for (Object obj : instances) {
			if (GameLogic.class.isAssignableFrom(obj.getClass())) {
				gameLogicInstances.add((GameLogic) obj);
			}
		}

		return gameLogicInstances;
	}

}
