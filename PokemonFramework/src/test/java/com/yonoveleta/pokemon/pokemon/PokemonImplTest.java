package com.yonoveleta.pokemon.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.move.MoveImpl;
import com.yonoveleta.pokemon.move.MoveType;
import com.yonoveleta.pokemon.status.Burn;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.type.Type;

class PokemonImplTest {

    private PokemonImpl pokemon;
    private List<Type> types;

    @BeforeEach
    void setUp() {
        types = Arrays.asList(Type.FIRE, Type.FLYING);
        pokemon = new PokemonImpl("Charizard", 100, 84, 78, 109, 85, 100, types);
    }

    @Test
    void testTakeDamage() {
        pokemon.takeDamage(50);
        assertEquals(50, pokemon.getHp());
    }

    @Test
    void testLevelUp() {
        int initialLevel = pokemon.getLevel();
        pokemon.levelUp();
        assertEquals(initialLevel + 1, pokemon.getLevel());
    }

    @Test
    void testLearnMove() {
        Move move = new MoveImpl("Flamethrower", 90, 100, Type.FIRE, MoveType.SPECIAL);
        pokemon.learnMove(move);
        assertTrue(pokemon.getMoveset().contains(move));
    }

    @Test
    void testForgetMove() {
    	Move move = new MoveImpl("Flamethrower", 90, 100, Type.FIRE, MoveType.SPECIAL);
        pokemon.learnMove(move);
        pokemon.forgetMove(0);
        assertFalse(pokemon.getMoveset().contains(move));
    }

    @Test
    void testAddStatus() {
        Status burn = new Burn();
        pokemon.addStatus(burn);
        assertTrue(pokemon.getStatuses().contains(burn));
    }

    @Test
    void testRemoveStatus() {
        Status burn = new Burn();
        pokemon.addStatus(burn);
        pokemon.removeStatus(burn);
        assertFalse(pokemon.getStatuses().contains(burn));
    }

}
