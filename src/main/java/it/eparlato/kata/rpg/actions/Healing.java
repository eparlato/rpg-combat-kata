package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.AttackRules;

public class Healing implements Action {
    private final Character healer;
    private final Character patient;
    private final int healingQuantity;
    private final AttackRules attackRules;

    public Healing(Character healer, Character patient, int healingQuantity, AttackRules attackRules) {
        this.healer = healer;
        this.patient = patient;
        this.healingQuantity = healingQuantity;
        this.attackRules = attackRules;
    }

    @Override
    public void execute() {
        if (attackRules.areAllies(healer, patient) || isCharacterHealingHimself()) {
            patient.receiveHealing(healingQuantity);
        }
    }

    private boolean isCharacterHealingHimself() {
        return healer.equals(patient);
    }
}
