package type;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TypeChart {
    private static final Map<Type, TypeEffectiveness> effectivenessMap = new EnumMap<>(Type.class);

    static {
        effectivenessMap.put(Type.NORMAL, new TypeEffectiveness(
                List.of(), 
                List.of(Type.ROCK, Type.STEEL), 
                List.of(Type.GHOST)
        ));

        effectivenessMap.put(Type.FIRE, new TypeEffectiveness(
                List.of(Type.GRASS, Type.ICE, Type.BUG, Type.STEEL),
                List.of(Type.WATER, Type.ROCK, Type.FIRE, Type.DRAGON),
                List.of()
        ));

        effectivenessMap.put(Type.WATER, new TypeEffectiveness(
                List.of(Type.FIRE, Type.GROUND, Type.ROCK),
                List.of(Type.WATER, Type.GRASS, Type.DRAGON),
                List.of()
        ));

        effectivenessMap.put(Type.ELECTRIC, new TypeEffectiveness(
                List.of(Type.WATER, Type.FLYING),
                List.of(Type.ELECTRIC, Type.GRASS, Type.DRAGON),
                List.of(Type.GROUND)
        ));

        effectivenessMap.put(Type.GRASS, new TypeEffectiveness(
                List.of(Type.WATER, Type.GROUND, Type.ROCK),
                List.of(Type.FIRE, Type.GRASS, Type.POISON, Type.FLYING, Type.BUG, Type.DRAGON, Type.STEEL),
                List.of()
        ));

        effectivenessMap.put(Type.ICE, new TypeEffectiveness(
                List.of(Type.GRASS, Type.GROUND, Type.FLYING, Type.DRAGON),
                List.of(Type.FIRE, Type.WATER, Type.ICE, Type.STEEL),
                List.of()
        ));

        effectivenessMap.put(Type.FIGHTING, new TypeEffectiveness(
                List.of(Type.NORMAL, Type.ICE, Type.ROCK, Type.DARK, Type.STEEL),
                List.of(Type.POISON, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.FAIRY),
                List.of(Type.GHOST)
        ));

        effectivenessMap.put(Type.POISON, new TypeEffectiveness(
                List.of(Type.GRASS, Type.FAIRY),
                List.of(Type.POISON, Type.GROUND, Type.ROCK, Type.GHOST),
                List.of(Type.STEEL)
        ));

        effectivenessMap.put(Type.GROUND, new TypeEffectiveness(
                List.of(Type.FIRE, Type.ELECTRIC, Type.POISON, Type.ROCK, Type.STEEL),
                List.of(Type.GRASS, Type.BUG),
                List.of(Type.FLYING)
        ));

        effectivenessMap.put(Type.FLYING, new TypeEffectiveness(
                List.of(Type.GRASS, Type.FIGHTING, Type.BUG),
                List.of(Type.ELECTRIC, Type.ROCK, Type.STEEL),
                List.of()
        ));

        effectivenessMap.put(Type.PSYCHIC, new TypeEffectiveness(
                List.of(Type.FIGHTING, Type.POISON),
                List.of(Type.PSYCHIC, Type.STEEL),
                List.of(Type.DARK)
        ));

        effectivenessMap.put(Type.BUG, new TypeEffectiveness(
                List.of(Type.GRASS, Type.PSYCHIC, Type.DARK),
                List.of(Type.FIRE, Type.FIGHTING, Type.POISON, Type.FLYING, Type.GHOST, Type.STEEL, Type.FAIRY),
                List.of()
        ));

        effectivenessMap.put(Type.ROCK, new TypeEffectiveness(
                List.of(Type.FIRE, Type.ICE, Type.FLYING, Type.BUG),
                List.of(Type.FIGHTING, Type.GROUND, Type.STEEL),
                List.of()
        ));

        effectivenessMap.put(Type.GHOST, new TypeEffectiveness(
                List.of(Type.PSYCHIC, Type.GHOST),
                List.of(Type.DARK),
                List.of(Type.NORMAL)
        ));

        effectivenessMap.put(Type.DRAGON, new TypeEffectiveness(
                List.of(Type.DRAGON),
                List.of(Type.STEEL),
                List.of(Type.FAIRY)
        ));

        effectivenessMap.put(Type.DARK, new TypeEffectiveness(
                List.of(Type.PSYCHIC, Type.GHOST),
                List.of(Type.FIGHTING, Type.DARK, Type.FAIRY),
                List.of()
        ));

        effectivenessMap.put(Type.STEEL, new TypeEffectiveness(
                List.of(Type.ICE, Type.ROCK, Type.FAIRY),
                List.of(Type.FIRE, Type.WATER, Type.ELECTRIC, Type.STEEL),
                List.of()
        ));

        effectivenessMap.put(Type.FAIRY, new TypeEffectiveness(
                List.of(Type.FIGHTING, Type.DRAGON, Type.DARK),
                List.of(Type.FIRE, Type.POISON, Type.STEEL),
                List.of()
        ));
    }

    public static double getEffectiveness(Type moveType, List<Type> targetTypes) {
        TypeEffectiveness effectiveness = effectivenessMap.get(moveType);
        if (effectiveness == null) return 1.0; // Default neutral if type not found

        double multiplier = 1.0;
        for (Type targetType : targetTypes) {
            multiplier *= effectiveness.getMultiplier(targetType);
        }
        return multiplier;
    }
    
}

