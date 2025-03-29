package com.yonoveleta.pokemon.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yonoveleta.pokemon.damage.DamageCalculator;
import com.yonoveleta.pokemon.damage.Gen1Calculator;
import com.yonoveleta.pokemon.effect.Effect;
import com.yonoveleta.pokemon.effect.StatusEffect;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonImpl;
import com.yonoveleta.pokemon.status.Flinch;
import com.yonoveleta.pokemon.type.Type;

class MoveImplTest {
    private MoveImpl move;
    private Pokemon user;
    private Pokemon target;
    private DamageCalculator calculator;
    List<Type> types1, types2;

    @BeforeEach
    void setUp() {
    	types1 = Arrays.asList(Type.FIRE, Type.FLYING);
    	types2 = Arrays.asList(Type.WATER);
        user = new PokemonImpl("Charizard", 100, 84, 78, 109, 85, 100, types1); // Example Pokémon, adjust as needed
        target = new PokemonImpl("Blastoise", 100, 10, 10, 10, 10, 10, types2);
        calculator = new Gen1Calculator(); // Use an actual implementation

        move = new MoveImpl("Tackle", 40, 35, Type.NORMAL, MoveType.PHYSICAL);
    }

    @Test
    void testConstructor() {
        assertEquals("Tackle", move.getName());
        assertEquals(40, move.getPower());
        assertEquals(35, move.getPPs());
        assertEquals(Type.NORMAL, move.getType());
        assertEquals(MoveType.PHYSICAL, move.getMoveType());
        assertTrue(move.getEffects().isEmpty());
    }

    @Test
    void testReducePPs() {
        move.reducePPs();
        assertEquals(34, move.getPPs());
    }

    @Test
    void testToStringFormat() {
        String expected = String.format("%-15s %-10d %-6d %-10s", "Tackle", 40, 35, Type.NORMAL);
        assertEquals(expected, move.toString());
    }

    @Test
    void testAddEffect() {
        Effect effect = new StatusEffect(new Flinch(), 1); // Example effect
        move.addEffect(effect);
        List<Effect> effects = move.getEffects();
        assertEquals(1, effects.size());
        assertEquals(effect, effects.get(0));
    }

    @Test
    void testExecute_DamageCalculation() {
        int initialHP = target.getHp();
        move.execute(user, target, calculator);

        // Assert that target took damage
        assertTrue(target.getHp() < initialHP);
    }

    @Test
    void testExecute_AppliesEffects() {
        Effect effect = new StatusEffect(new Flinch(), 1);
        MoveImpl moveWithEffect = new MoveImpl("Tackle", 40, 35, Type.NORMAL, MoveType.PHYSICAL, Arrays.asList(effect));

        moveWithEffect.execute(user, target, calculator);

        // Check if effect was applied (assuming target has a way to check active effects)
        assertTrue(target.hasStatus(Flinch.class));
    }
}