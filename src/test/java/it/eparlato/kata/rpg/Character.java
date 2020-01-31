package it.eparlato.kata.rpg;

import java.util.ArrayList;
import java.util.List;

class Character {
    public static final int MAX_HEALTH = 1000;
    public static final int DAMAGE_REDUCTION_THRESHOLD = 5;
    public static final int DAMAGE_AMPLIFICATION_THRESHOLD = 5;
    private int health;
    private int level;
    private List<Faction> factionsJoined = new ArrayList<>();

    public Character() {
        this(1);
    }

    public Character(int level) {
        this.level = level;
        this.health = MAX_HEALTH;
    }

    public int health() {
        return this.health;
    }

    public void attack(Character target, int damage) {
        if (target.maxRange() > this.maxRange()) {
            return;
        }

        target.receiveAttackFrom(this, damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int healing) {
        health += healing;

        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    public int maxRange() {
        return 1;
    }

    private void receiveAttackFrom(Character attacker, int damageEffort) {
        health -= computeDealtDamage(attacker, damageEffort);

        if (health < 0) {
            health = 0;
        }
    }

    private int computeDealtDamage(Character attacker, int damage) {
        if (shouldReduceReceivedDamageFrom(attacker)) {
            return halfOf(damage);
        }
        if (shouldIncreaseReceivedDamageFrom(attacker)) {
            return damage + halfOf(damage);
        }
        return damage;
    }

    private int halfOf(int damage) {
        return damage / 2;
    }

    private boolean shouldIncreaseReceivedDamageFrom(Character attacker) {
        return (attacker.level - level) >= DAMAGE_AMPLIFICATION_THRESHOLD;
    }

    private boolean shouldReduceReceivedDamageFrom(Character attacker) {
        return (level - attacker.level) >= DAMAGE_REDUCTION_THRESHOLD;
    }

    public List<Faction> factionsJoined() {
        return factionsJoined;
    }

    public void join(Faction faction) {
        factionsJoined.add(faction);
    }
}
