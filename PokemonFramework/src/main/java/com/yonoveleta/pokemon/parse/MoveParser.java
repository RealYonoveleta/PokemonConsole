package com.yonoveleta.pokemon.parse;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.move.MoveImpl;

public class MoveParser extends AbstractParser<Move> {

	private String moveFile;

	public MoveParser(String moveFile) {
		this.moveFile = moveFile;
	}

	@Override
	protected String getFilePath() {
		return moveFile;
	}

	@Override
	protected Type getListType() {
		return new TypeToken<List<MoveImpl>>() {}.getType();
	}

}
