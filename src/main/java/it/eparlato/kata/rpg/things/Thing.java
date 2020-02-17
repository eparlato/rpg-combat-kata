package it.eparlato.kata.rpg.things;

public abstract class Thing {
    protected int health;

    public int health() {
        return health;
    }

    public void receiveDamage(int damageDealt) {
        this.health -= damageDealt;

        if (this.health < 0) {
            this.health = 0;
        }
    }

    public Status status() {
        if (health == 0)
            return Status.DESTROYED;

        return Status.INTACT;
    }
}
