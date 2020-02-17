package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.things.Thing;

public class AttackThing implements Action {
    private final Thing target;
    private final int damageEffort;

    public AttackThing(Thing target, int damageEffort) {
        this.target = target;
        this.damageEffort = damageEffort;
    }

    @Override
    public void execute() {
        target.receiveDamage(damageEffort);
    }
}
