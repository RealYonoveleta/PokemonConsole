package type;

import java.util.List;

public enum Type {
	NORMAL,
    FIRE,
    WATER,
    ELECTRIC,
    GRASS,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DRAGON,
    DARK,
    STEEL,
    FAIRY;
	
	private List<Type> strengths;
	private List<Type> weaknesses;
	
	static {
		NORMAL.strengths = List.of();
        NORMAL.weaknesses = List.of(ROCK, STEEL);

        FIRE.strengths = List.of(GRASS, ICE, BUG, STEEL);
        FIRE.weaknesses = List.of(FIRE, WATER, ROCK, DRAGON);

        WATER.strengths = List.of(FIRE, GROUND, ROCK);
        WATER.weaknesses = List.of(WATER, GRASS, DRAGON);

        ELECTRIC.strengths = List.of(WATER, FLYING);
        ELECTRIC.weaknesses = List.of(ELECTRIC, GRASS, DRAGON);

        GRASS.strengths = List.of(WATER, GROUND, ROCK);
        GRASS.weaknesses = List.of(FIRE, GRASS, POISON, FLYING, BUG, DRAGON, STEEL);

        ICE.strengths = List.of(GRASS, GROUND, FLYING, DRAGON);
        ICE.weaknesses = List.of(FIRE, WATER, ICE, STEEL);

        FIGHTING.strengths = List.of(NORMAL, ICE, ROCK, DARK, STEEL);
        FIGHTING.weaknesses = List.of(POISON, FLYING, PSYCHIC, BUG, FAIRY);

        POISON.strengths = List.of(GRASS, FAIRY);
        POISON.weaknesses = List.of(POISON, GROUND, ROCK, GHOST);

        GROUND.strengths = List.of(FIRE, ELECTRIC, POISON, ROCK, STEEL);
        GROUND.weaknesses = List.of(GRASS, BUG);

        FLYING.strengths = List.of(GRASS, FIGHTING, BUG);
        FLYING.weaknesses = List.of(ELECTRIC, ROCK, STEEL);

        PSYCHIC.strengths = List.of(FIGHTING, POISON);
        PSYCHIC.weaknesses = List.of(PSYCHIC, STEEL);

        BUG.strengths = List.of(GRASS, PSYCHIC, DARK);
        BUG.weaknesses = List.of(FIRE, FIGHTING, POISON, FLYING, GHOST, STEEL, FAIRY);

        ROCK.strengths = List.of(FIRE, ICE, FLYING, BUG);
        ROCK.weaknesses = List.of(FIGHTING, GROUND, STEEL);

        GHOST.strengths = List.of(PSYCHIC, GHOST);
        GHOST.weaknesses = List.of(DARK);

        DRAGON.strengths = List.of(DRAGON);
        DRAGON.weaknesses = List.of(STEEL);

        DARK.strengths = List.of(PSYCHIC, GHOST);
        DARK.weaknesses = List.of(DARK, FAIRY);

        STEEL.strengths = List.of(ICE, ROCK, FAIRY);
        STEEL.weaknesses = List.of(FIRE, WATER, ELECTRIC, STEEL);

        FAIRY.strengths = List.of(FIGHTING, DRAGON, DARK);
        FAIRY.weaknesses = List.of(FIRE, POISON, STEEL);
    }
	
	public List<Type> getStrengths() {
	    return strengths;
	}

	public List<Type> getWeaknesses() {
	    return weaknesses;
	}
}
