package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.GameContext;

public class GameContextScanner extends AbstractAnnotationScanner<GameContext> {
	
	public GameContextScanner() {
		super(GameContext.class, Scanners.TypesAnnotated);
	}

}
