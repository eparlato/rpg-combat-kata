package it.eparlato.kata.rpg.things;

public class Tree implements Thing {
    private final int health;

    public Tree(int health) {
        this.health = health;
    }

    @Override
    public int health() {
        return health;
    }
}
