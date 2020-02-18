package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Faction;
import it.eparlato.kata.rpg.things.Thing;

public class Accept implements Action {
    private final Faction faction;

    public Accept(Faction faction) {
        this.faction = faction;
    }

    @Override
    public void on(Character character) {
        faction.accept(character);
    }

    @Override
    public void on(Thing target) {

    }
}
