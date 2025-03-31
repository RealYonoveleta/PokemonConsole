package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.ui.MoveUI;
import com.yonoveleta.pokemon.ui.impl.DefaultMoveUI;

public class MoveUIManager implements MoveUI {
	
	private static MoveUIManager instance = new MoveUIManager();

    private MoveUI moveUI;  // The UI being used for all Moves

    public MoveUIManager() {
        this.moveUI = new DefaultMoveUI();
    }

    public static synchronized MoveUIManager getInstance() {
        return instance;
    }

    public void setMoveUI(MoveUI newUI) {
        this.moveUI = newUI;
    }

    public MoveUI getMoveUI() {
        return this.moveUI;
    }

	@Override
	public void displayMoveUsed(String moveName, Pokemon source) {
		moveUI.displayMoveUsed(moveName, source);
	}

	@Override
	public void displaySuperEffective() {
		moveUI.displaySuperEffective();
	}

	@Override
	public void displayNotVeryEffective() {
		moveUI.displayNotVeryEffective();
	}

	@Override
	public void displayNoPP(String moveName) {
		moveUI.displayNoPP(moveName);
	}

	@Override
	public void displayDamageDealt(Pokemon target, int damage) {
		moveUI.displayDamageDealt(target, damage);
	}

	@Override
	public void displayMoveFailed() {
		moveUI.displayMoveFailed();
	}

	@Override
	public void displayAttackMessage(Pokemon user, Move move, Pokemon target, int damage) {
		moveUI.displayAttackMessage(user, move, target, damage);
	}

	@Override
	public void displayStatusMoveMessage(Pokemon user, Move move, Pokemon target) {
		moveUI.displayStatusMoveMessage(user, move, target);
	}

	@Override
	public void displayEffectivenessMessage(double multiplier) {
		moveUI.displayEffectivenessMessage(multiplier);
	}

}
