package com.yonoveleta.pokemon.movepool;

import com.yonoveleta.pokemon.move.Move;

public class MoveLevel {
    private Move move;
    private int level;

    public MoveLevel(Move move, int level) {
        this.move = move;
        this.level = level;
    }

    public Move getMove() {
        return move;
    }

    public int getLevel() {
        return level;
    } 
    
}