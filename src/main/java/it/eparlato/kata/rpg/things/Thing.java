package it.eparlato.kata.rpg.things;

public interface Thing {
    int health();

    void receiveDamage(int damageDealt);
}
