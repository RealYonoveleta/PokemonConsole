package com.yonoveleta.pokemon.stat;

public class Stat {
    private int baseValue;  // The original stat value
    private int stageModifier; // -6 to +6, like in Pokémon games

    public Stat(int baseValue) {
        this.baseValue = baseValue;
        this.stageModifier = 0; // Default modifier is 0
    }

    public void modifyStage(int amount) {
        stageModifier = Math.max(-6, Math.min(6, stageModifier + amount)); // Clamp to -6 to +6
    }

    public int getModifiedValue() {
        double[] multipliers = { 0.25, 0.285, 0.33, 0.4, 0.5, 0.66, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0 };
        return (int) (baseValue * multipliers[stageModifier + 6]); // Adjust index for -6 to +6
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getStageModifier() {
        return stageModifier;
    }
    
    public void reset() {
        this.stageModifier = 0; // Reset back to normal stage (no buffs/debuffs)
    }
    
}

