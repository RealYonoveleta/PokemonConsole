package com.yonoveleta.pokemon.factory;

import java.io.FileNotFoundException;
import java.util.List;

import com.yonoveleta.pokemon.di.annotation.Factory;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.move.MoveImpl;
import com.yonoveleta.pokemon.parse.MoveParser;

@Factory
public class MoveFactory extends AbstractGameFactory<String, Move> {

	private static final MoveParser moveParser = new MoveParser("data/moves.json");
	private static final MoveFactory INSTANCE = new MoveFactory();

	private MoveFactory() {
		super();
	}

	public static MoveFactory getInstance() {
		return INSTANCE;
	}

	@Override
	protected List<Move> loadData() throws FileNotFoundException {
		return moveParser.parse();
	}

	@Override
	public String getKey(Move move) {
		return move.getName();
	}

	@Override
	protected Move createCopy(Move move) {
		return new MoveImpl(move); 
	}

}
