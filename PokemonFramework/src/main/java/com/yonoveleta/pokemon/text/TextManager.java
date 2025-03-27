package com.yonoveleta.pokemon.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.yonoveleta.pokemon.di.annotation.Loader;
import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.io.log.CentralLogger;

@Loader
public class TextManager {

	private static final TextManager INSTANCE = new TextManager();
	private final Map<String, String> texts = new HashMap<>();
	private static ConsoleHandler console = ConsoleHandler.getInstance();

	private TextManager() {
	}

	public static TextManager getInstance() {
		return INSTANCE;
	}

	public void loadTexts(File directory) {
		File[] files = directory.listFiles((_, name) -> name.endsWith(".yaml"));
		if (files != null) {
			for (File file : files) {
				loadTextsFromFile(file);
				CentralLogger.logInfo("Loaded text-file: %s", file);
			}
		}
	}

	private void loadTextsFromFile(File file) {
		Yaml yaml = new Yaml();
		try (FileInputStream fis = new FileInputStream(file)) {
			Map<String, String> loadedData = yaml.load(fis);
			loadedData.forEach((key, value) -> texts.put(key, value));
		} catch (IOException e) {
			CentralLogger.logError("Error loading text-file " + file, e);
		}
	}

	public String get(String title) {
		return texts.getOrDefault(title, "Text not found.");
	}

	public void show(String... texts) {
		for (String text : texts) {
			console.displayMessage(get(text));
		}
	}

}
