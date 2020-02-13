package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Faction;

public class Accept implements Action {
    private final Faction faction;
    private final Character character;

    public Accept(Faction faction, Character character) {
        this.faction = faction;
        this.character = character;
    }

    @Override
    public void execute() {
        faction.accept(character);
    }
}
