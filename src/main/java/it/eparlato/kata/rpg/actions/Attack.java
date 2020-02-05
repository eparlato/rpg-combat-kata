package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;

import static it.eparlato.kata.rpg.Character.DAMAGE_AMPLIFICATION_THRESHOLD;
import static it.eparlato.kata.rpg.Character.DAMAGE_REDUCTION_THRESHOLD;

public class Attack implements Action {
    private final Character attacker;
    private final Character target;
    private final int damageEffort;

    public Attack(Character attacker, Character target, int damageEffort) {
        this.attacker = attacker;
        this.target = target;
        this.damageEffort = damageEffort;
    }

    @Override
    public void execute() {
        if (target.isAlliedWith(attacker)) {
            return;
        }

        if (target.maxRange() > attacker.maxRange()) {
            return;
        }

        int damageDealt = computeDealtDamage(attacker, target, damageEffort);

        target.receiveDamage(damageDealt);
    }

    private int computeDealtDamage(Character attacker, Character target, int damageEffort) {
        if (shouldTargetReduceReceivedDamageFromAttacker(attacker, target)) {
            return halfOf(damageEffort);
        }
        if (shouldTargetIncreaseReceivedDamageFromAttacker(attacker, target)) {
            return damageEffort + halfOf(damageEffort);
        }
        return damageEffort;
    }

    private boolean shouldTargetIncreaseReceivedDamageFromAttacker(Character attacker, Character target) {
        return (attacker.getLevel() - target.getLevel()) >= DAMAGE_AMPLIFICATION_THRESHOLD;
    }

    private boolean shouldTargetReduceReceivedDamageFromAttacker(Character attacker, Character target) {
        return (target.getLevel() - attacker.getLevel()) >= DAMAGE_REDUCTION_THRESHOLD;
    }

    private int halfOf(int damage) {
        return damage / 2;
    }
}
