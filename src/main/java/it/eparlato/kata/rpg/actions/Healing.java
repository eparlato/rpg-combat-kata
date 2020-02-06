package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;

public class Healing implements Action {
    private final Character healer;
    private final Character patient;
    private final int healingQuantity;

    public Healing(Character healer, Character patient, int healingQuantity) {
        this.healer = healer;
        this.patient = patient;
        this.healingQuantity = healingQuantity;
    }

    @Override
    public void execute() {
        patient.receiveHealing(healingQuantity);
    }
}
