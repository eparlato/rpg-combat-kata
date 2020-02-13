package it.eparlato.kata.rpg;

public class Character {
    public static final int MAX_HEALTH = 1000;
    public static final int DAMAGE_REDUCTION_THRESHOLD = 5;
    public static final int DAMAGE_AMPLIFICATION_THRESHOLD = 5;
    private int health;

    private int level;

    public Character() {
        this(1);
    }

    public Character(int level) {
        this.level = level;
        this.health = MAX_HEALTH;
    }

    public int getLevel() {
        return level;
    }

    public int health() {
        return this.health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void receiveDamage(int damageDealt) {
        health -= damageDealt;

        if (health < 0) {
            health = 0;
        }
    }

    public void receiveHealing(int healing) {
        health += healing;

        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    public int maxRange() {
        return 1;
    }
}
