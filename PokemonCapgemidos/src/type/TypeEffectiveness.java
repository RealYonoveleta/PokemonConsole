package type;

import java.util.List;

public class TypeEffectiveness {
    public final List<Type> strengths; // x2 damage
    public final List<Type> weaknesses; // x0.5 damage
    public final List<Type> noEffect; // x0 damage

    public TypeEffectiveness(List<Type> strengths, List<Type> weaknesses, List<Type> noEffect) {
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.noEffect = noEffect;
    }
    
    public double getMultiplier(Type targetType) {
        if (noEffect.contains(targetType)) {
            return 0.0;  // Completely ineffective
        } else if (strengths.contains(targetType)) {
            return 2.0;  // Super effective
        } else if (weaknesses.contains(targetType)) {
            return 0.5;  // Not very effective
        }
        return 1.0;  // Neutral
    }
}
