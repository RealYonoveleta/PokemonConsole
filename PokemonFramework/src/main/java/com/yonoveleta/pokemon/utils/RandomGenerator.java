package com.yonoveleta.pokemon.utils;

import java.util.Random;

public class RandomGenerator {

	private static final Random instance = new Random();

    private RandomGenerator() {}

    public static Random getInstance() {
        return instance;
    }
	
}
