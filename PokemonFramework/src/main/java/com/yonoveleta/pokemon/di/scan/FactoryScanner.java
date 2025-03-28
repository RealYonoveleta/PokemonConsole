package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.Factory;

public class FactoryScanner extends AbstractAnnotationScanner<Factory> {
	
	public FactoryScanner() {
		super(Factory.class, Scanners.TypesAnnotated);
	}

}
