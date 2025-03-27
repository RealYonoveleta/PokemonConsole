package com.yonoveleta.pokemon.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.yonoveleta.pokemon.io.log.CentralLogger;
import com.yonoveleta.pokemon.text.TextManager;

public class DataInitializer {

	public static void ensureUserDataExists() {
		File userDataDir = new File(DataPathProvider.USER_DATA_FOLDER);
		File userTextDir = new File(DataPathProvider.USER_DATA_FOLDER, DataPathProvider.USER_TEXT_FOLDER);

		// Ensure the user-data directory exists
		if (!userDataDir.exists()) {
			userDataDir.mkdirs();
		}
		
		// Ensure the user-data/text directory exists
		if (!userTextDir.exists()) {
			userTextDir.mkdir();
		}

		copyFileIfNotExists(DataPathProvider.POKEMON_DATA);
		copyFileIfNotExists(DataPathProvider.MOVE_DATA);
		
		File textsDirectory = new File(DataPathProvider.USER_DATA_FOLDER, DataPathProvider.USER_TEXT_FOLDER);
		TextManager.getInstance().loadTexts(textsDirectory);
	}

	private static void copyFileIfNotExists(String resourcePath) {
		File destFile = new File(DataPathProvider.USER_DATA_FOLDER, new File(resourcePath).getName());
		if (destFile.exists()) {
			CentralLogger.logInfo("%s already exists", destFile);
			return; // Don't overwrite existing user files
		}

		try (InputStream in = DataInitializer.class.getClassLoader().getResourceAsStream(resourcePath);
				OutputStream out = new FileOutputStream(destFile)) {
			if (in == null) {
				CentralLogger.logError("Missing default data: " + resourcePath, null);
				return;
			}
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			CentralLogger.logInfo("%s created", destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
