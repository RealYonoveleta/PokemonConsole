package com.yonoveleta.pokemon.parse;

import java.io.FileNotFoundException;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface Parser<T> {
	
	List<T> parse() throws JsonIOException, JsonSyntaxException, FileNotFoundException;

}
