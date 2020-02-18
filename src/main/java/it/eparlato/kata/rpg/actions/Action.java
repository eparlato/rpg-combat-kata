package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.things.Thing;

public interface Action {
    void on(Character target);
    void on(Thing target);
}
