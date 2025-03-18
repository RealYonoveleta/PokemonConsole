package turn;

import java.util.Comparator;

public class TurnComparator implements Comparator<TurnAction> {
    @Override
    public int compare(TurnAction a, TurnAction b) {
        if (a.move.getPriority() != b.move.getPriority()) {
            return Integer.compare(b.move.getPriority(), a.move.getPriority()); // Higher priority first
        }
        if (a.user.getSpeed() != b.user.getSpeed()) {
            return Integer.compare(b.user.getSpeed(), a.user.getSpeed()); // Faster Pokémon first
        }
        return Math.random() < 0.5 ? -1 : 1; // Random tiebreaker if speed is the same
    }
}

