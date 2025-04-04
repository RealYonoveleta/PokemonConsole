package com.yonoveleta.pokemon.env;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.yonoveleta.pokemon.annotation.scan.AnnotationScanner;
import com.yonoveleta.pokemon.data.DataInitializer;
import com.yonoveleta.pokemon.data.DataPathProvider;
import com.yonoveleta.pokemon.data.annotation.DataPath;
import com.yonoveleta.pokemon.di.AnnotationRegistry;
import com.yonoveleta.pokemon.di.DependencyInjector;
import com.yonoveleta.pokemon.di.annotation.Logic;
import com.yonoveleta.pokemon.di.comparator.LogicComparator;
import com.yonoveleta.pokemon.io.log.CentralLogger;

public class PokemonApp {
	// Method to initialize the entire engine/environment
	public static void start() {
		// Initialize ServiceLoader for dynamic loading of modules, etc.
		initializeServiceLoader();

		// Classpath scanning
		Class<?> app = getPokemonApplication();

		// Loading data
		DataPath dataPath = app.getAnnotation(DataPath.class);
		if (dataPath != null)
			DataPathProvider.USER_DATA_FOLDER = dataPath.value();
		CentralLogger.logInfo("Set default user-data path to %s", DataPathProvider.USER_DATA_FOLDER);
		DataInitializer.ensureUserDataExists();

		// Depencendy injection
		AnnotationRegistry registry = AnnotationRegistry.getInstance();
		DependencyInjector.initializeInstances(registry);
		DependencyInjector.injectDependencies(registry);

		// Get game logic classes
		List<GameLogic> logics = getLogicInstances(registry);

		// Any other initialization logic can go here
		CentralLogger.logInfo("Engine initialized successfully!");
		System.out.println("-".repeat(100));

		for (GameLogic logic : logics) {
			logic.run();
		}
	}

	private static void initializeServiceLoader() {
		ServiceLoader.load(AnnotationScanner.class).forEach(AnnotationScanner::scan);
	}

	public static Class<?> getPokemonApplication() {
		return AnnotationRegistry.getInstance().getPokemonApplicationClass();
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
			CentralLogger.logInfo("Loaded logic class: %s", clazz);
		}

		instances = instances.stream().sorted(new LogicComparator()).collect(Collectors.toList());

		for (Object obj : instances) {
			if (GameLogic.class.isAssignableFrom(obj.getClass())) {
				gameLogicInstances.add((GameLogic) obj);
			}
		}

		return gameLogicInstances;
	}

}
