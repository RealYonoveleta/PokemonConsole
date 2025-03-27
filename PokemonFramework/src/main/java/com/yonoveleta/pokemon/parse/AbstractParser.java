package com.yonoveleta.pokemon.parse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.yonoveleta.pokemon.data.DataPathProvider;

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

			// Use TypeToken to specify the generic type for deserialization
			return GsonRegistry.getInstance().getGson().fromJson(new InputStreamReader(inputStream), getListType());

		} catch (IOException e) {
			System.err.println("I/O Error while reading file: " + getFilePath());
			e.printStackTrace();
		} catch (JsonSyntaxException | JsonIOException e) {
			System.err.println("Error parsing JSON file: " + getFilePath());
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.err.println("Failed to close input stream: " + getFilePath());
				}
			}
		}

		return Collections.emptyList(); // Return empty list on failure
	}

}
