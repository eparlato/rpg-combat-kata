package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;

public class Attack implements Action {
    private final Character attacker;
    private final Character target;
    private final int damageDealt;

    public Attack(Character attacker, Character target, int damageDealt) {
        this.attacker = attacker;
        this.target = target;
        this.damageDealt = damageDealt;
    }

    @Override
    public void execute() {
        if (target.isAlliedWith(attacker)) {
            return;
        }

        if (target.maxRange() > attacker.maxRange()) {
            return;
        }

        target.receiveAttackFrom(attacker, damageDealt);
    }
}
