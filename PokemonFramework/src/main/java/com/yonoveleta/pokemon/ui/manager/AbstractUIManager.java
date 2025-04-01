package com.yonoveleta.pokemon.ui.manager;

public class AbstractUIManager<UI> implements UIManager<UI> {
	
	protected UI activeUI;

	@Override
	public void setUI(UI newUI) {
		this.activeUI = newUI;
	}

	@Override
	public UI getUI() {
		return this.activeUI;
	}

}
