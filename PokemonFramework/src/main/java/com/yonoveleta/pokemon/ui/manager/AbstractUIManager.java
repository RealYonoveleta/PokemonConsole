package com.yonoveleta.pokemon.ui.manager;

public class AbstractUIManager<T> implements UIManager<T> {
	
	protected T activeUI;

	@Override
	public void setUI(T newUI) {
		this.activeUI = newUI;
	}

	@Override
	public T getUI() {
		return this.activeUI;
	}

}
