package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.Loader;

public class LoaderScanner extends AbstractAnnotationScanner<Loader>{

	public LoaderScanner() {
		super(Loader.class, Scanners.TypesAnnotated);
	}
	
}
