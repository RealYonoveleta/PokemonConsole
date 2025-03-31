package com.yonoveleta.pokemon.parse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.yonoveleta.pokemon.data.DataPathProvider;
import com.yonoveleta.pokemon.io.log.CentralLogger;

public abstract class AbstractParser<T> implements Parser<T> {

	// Abstract method that subclasses will implement to specify the file path
	protected abstract String getFilePath();

	// Get the file name
	protected String getFileName() {
		Path path = Paths.get(getFilePath());
		return path.getFileName().toString();
	}

	// Abstract method that subclasses will implement to specify the list type
	protected abstract Type getListType();

	public List<T> parse() {
		InputStream inputStream = null;
		try {
			// First, try to load from the user's custom path
			Path userFilePath = Paths.get(DataPathProvider.USER_DATA_FOLDER, getFileName());
			if (Files.exists(userFilePath)) {
				inputStream = Files.newInputStream(userFilePath);
			} else {
				// If not found, fall back to framework resources
				inputStream = getClass().getClassLoader().getResourceAsStream(getFilePath());

				if (inputStream == null) {
					System.err.println("Error: File not found in resources or user path: " + getFilePath());
					return Collections.emptyList(); // Return empty list instead of throwing
				}
			}

			return deserializeJson(inputStream);

		} catch (IOException e) {
			CentralLogger.logError("I/O Error while reading file: " + getFilePath(), e);
		} catch (JsonSyntaxException | JsonIOException e) {
			CentralLogger.logError("Error parsing JSON file: " + getFilePath(), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					CentralLogger.logError("Failed to close input stream: " + getFilePath(), e);
				}
			}
		}

		return Collections.emptyList(); // Return empty list on failure
	}
	
	// Method that handles the deserialization
	private List<T> deserializeJson(InputStream inputStream) {
	    try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
	        return GsonRegistry.getInstance().getGson().fromJson(inputStreamReader, getListType());
	    } catch (IOException e) {
	        CentralLogger.logError("Error parsing", e);
	        return new ArrayList<>();
	    }
	}

}
