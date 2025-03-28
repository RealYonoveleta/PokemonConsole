package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.PokemonApplication;

public class PokemonApplicationScanner extends AbstractAnnotationScanner<PokemonApplication> {

	public PokemonApplicationScanner() {
		super(PokemonApplication.class, Scanners.TypesAnnotated);
	}

}
