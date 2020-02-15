package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Council;

public class Healing implements Action {
    private final Character healer;
    private final Character patient;
    private final int healingQuantity;
    private final Council council;

    public Healing(Character healer, Character patient, int healingQuantity, Council council) {
        this.healer = healer;
        this.patient = patient;
        this.healingQuantity = healingQuantity;
        this.council = council;
    }

    @Override
    public void execute() {
        if (council.areAllies(healer, patient) || isCharacterHealingHimself()) {
            patient.receiveHealing(healingQuantity);
        }
    }

    private boolean isCharacterHealingHimself() {
        return healer.equals(patient);
    }
}
