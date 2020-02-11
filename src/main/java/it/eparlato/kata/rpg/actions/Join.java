package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Faction;

public class Join implements Action {
    private final Faction faction;
    private final Character character;

    public Join(Faction faction, Character character) {
        this.faction = faction;
        this.character = character;
    }

    @Override
    public void execute() {
        faction.accept(character);
    }
}
