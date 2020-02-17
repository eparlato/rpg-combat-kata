package it.eparlato.kata.rpg.things;

public class Tree implements Thing {
    public static final int MAX_TREE_HEALTH = 2000;
    private int health;

    public Tree() {
        this.health = MAX_TREE_HEALTH;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public void receiveDamage(int damageDealt) {
        this.health -= damageDealt;
    }
}
