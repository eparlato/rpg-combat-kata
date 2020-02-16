package it.eparlato.kata.rpg;

import java.util.Set;

public class AttackRules {
    private final Set<Faction> factions;

    public AttackRules(Set<Faction> factions) {
        this.factions = factions;
    }

    public boolean areAllies(Character fellow, Character otherFellow) {
        for (Faction faction : factions) {
            if (faction.areAllies(fellow, otherFellow)) {
                return true;
            }
        }

        return false;
    }

}
