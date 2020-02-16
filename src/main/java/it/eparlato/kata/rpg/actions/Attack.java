package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.AttackRules;

import static it.eparlato.kata.rpg.Character.DAMAGE_AMPLIFICATION_THRESHOLD;
import static it.eparlato.kata.rpg.Character.DAMAGE_REDUCTION_THRESHOLD;

public class Attack implements Action {
    private final Character attacker;
    private final Character target;
    private final int damageEffort;
    private final AttackRules attackRules;

    public Attack(Character attacker, Character target, int damageEffort, AttackRules attackRules) {
        this.attacker = attacker;
        this.target = target;
        this.damageEffort = damageEffort;
        this.attackRules = attackRules;
    }

    @Override
    public void execute() {
        int damageDealt = computeDealtDamage(attacker, target, damageEffort);

        target.receiveDamage(damageDealt);
    }

    private int computeDealtDamage(Character attacker, Character target, int damageEffort) {
        if (attackRules.areAllies(attacker, target)) {
            return 0;
        }

        if (target.maxRange() > attacker.maxRange()) {
            return 0;
        }

        if (shouldBeDamageReducedByHalf(attacker, target)) {
            return halfOf(damageEffort);
        }
        if (shouldBeDamageIncreasedByHalf(attacker, target)) {
            return damageEffort + halfOf(damageEffort);
        }
        return damageEffort;
    }

    private boolean shouldBeDamageIncreasedByHalf(Character attacker, Character target) {
        return (attacker.getLevel() - target.getLevel()) >= DAMAGE_AMPLIFICATION_THRESHOLD;
    }

    private boolean shouldBeDamageReducedByHalf(Character attacker, Character target) {
        return (target.getLevel() - attacker.getLevel()) >= DAMAGE_REDUCTION_THRESHOLD;
    }

    private int halfOf(int damage) {
        return damage / 2;
    }
}
