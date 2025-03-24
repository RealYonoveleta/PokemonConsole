package turn;

import java.util.Comparator;

public class TurnComparator implements Comparator<TurnAction> {
    @Override
    public int compare(TurnAction a, TurnAction b) {
        if (a.getMove().getPriority() != b.getMove().getPriority()) {
            return Integer.compare(b.getMove().getPriority(), a.getMove().getPriority()); // Higher priority first
        }
        if (a.getUser().getSpeed() != b.getUser().getSpeed()) {
            return Integer.compare(b.getUser().getSpeed(), a.getUser().getSpeed()); // Faster Pokémon first
        }
        return Math.random() < 0.5 ? -1 : 1; // Random tiebreaker if speed is the same
    }
}

