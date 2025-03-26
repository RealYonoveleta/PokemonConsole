package com.yonoveleta.pokemon.parse;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public abstract class AbstractParser<T> {

	// Abstract method that subclasses will implement to specify the file path
	protected abstract String getFilePath();

	// Abstract method that subclasses will implement to specify the list type
	protected abstract Type getListType();

	public List<T> parse() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		// Load file from resources
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getFilePath());

		if (inputStream == null) {
			throw new FileNotFoundException("File not found in resources: " + getFilePath());
		}

		// Use TypeToken to specify the generic type for deserialization
	    List<T> items = GsonRegistry.getInstance().getGson().fromJson(
	            new InputStreamReader(inputStream),
	            getListType()); // Pass TypeToken to handle generic type
	    
	    return items;
	}
}
