package com.yonoveleta.pokemon.ui.manager;

public interface UIManager<T> {

	void setUI(T newUI);
	
	T getUI();
	
}
