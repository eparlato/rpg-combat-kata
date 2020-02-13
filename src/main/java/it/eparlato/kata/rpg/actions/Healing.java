package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;

public class Healing implements Action {
    private final Character healer;
    private final Character patient;
    private final int healingQuantity;
    private final boolean areAllies;

    public Healing(Character healer, Character patient, int healingQuantity, boolean areAllies) {
        this.healer = healer;
        this.patient = patient;
        this.healingQuantity = healingQuantity;
        this.areAllies = areAllies;
    }

    @Override
    public void execute() {
        if (areAllies || isCharacterHealingHimself()) {
            patient.receiveHealing(healingQuantity);
        }
    }

    private boolean isCharacterHealingHimself() {
        return healer.equals(patient);
    }
}
