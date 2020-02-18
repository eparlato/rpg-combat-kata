package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.AttackRules;
import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.things.Thing;

import static it.eparlato.kata.rpg.Character.DAMAGE_AMPLIFICATION_THRESHOLD;
import static it.eparlato.kata.rpg.Character.DAMAGE_REDUCTION_THRESHOLD;

public class Attack implements Action {

    private final Character attacker;
    private final int damageEffort;
    private final AttackRules attackRules;

    public Attack(Character attacker, int damageEffort, AttackRules attackRules) {
        this.attacker = attacker;
        this.damageEffort = damageEffort;
        this.attackRules = attackRules;
    }

    @Override
    public void on(Character target) {
        int damageDealt = computeDealtDamage(attacker, target, damageEffort);

        target.receiveDamage(damageDealt);
    }

    @Override
    public void on(Thing target) {
        target.receiveDamage(damageEffort);
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
