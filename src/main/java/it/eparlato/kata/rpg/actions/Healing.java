package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.AttackRules;
import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.things.Thing;

public class Healing implements Action {
    private final Character healer;
    private final int healingQuantity;
    private final AttackRules attackRules;

    public Healing(Character healer, int healingQuantity, AttackRules attackRules) {
        this.healer = healer;
        this.healingQuantity = healingQuantity;
        this.attackRules = attackRules;
    }

    @Override
    public void on(Character patient) {
        if (attackRules.areAllies(healer, patient) || isCharacterHealingHimself(patient)) {
            patient.receiveHealing(healingQuantity);
        }
    }

    @Override
    public void on(Thing target) {

    }

    private boolean isCharacterHealingHimself(Character patient) {
        return healer.equals(patient);
    }
}
