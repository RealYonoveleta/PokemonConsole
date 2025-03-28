package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.Inject;

public class InjectScanner extends AbstractAnnotationScanner<Inject> {

	public InjectScanner() {
		super(Inject.class, Scanners.FieldsAnnotated);
	}
	
}
