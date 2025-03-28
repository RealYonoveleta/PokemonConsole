package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.Logic;

public class LogicScanner extends AbstractAnnotationScanner<Logic> {
	
	public LogicScanner() {
		super(Logic.class, Scanners.TypesAnnotated);
	}

}
