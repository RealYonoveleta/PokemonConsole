package com.yonoveleta.pokemon.di.scan;

import org.reflections.scanners.Scanners;

import com.yonoveleta.pokemon.annotation.scan.AbstractAnnotationScanner;
import com.yonoveleta.pokemon.di.annotation.UIManagerType;

public class UIManagerTypeScanner extends AbstractAnnotationScanner<UIManagerType>{

	public UIManagerTypeScanner() {
		super(UIManagerType.class, Scanners.TypesAnnotated);
	}
	
}
