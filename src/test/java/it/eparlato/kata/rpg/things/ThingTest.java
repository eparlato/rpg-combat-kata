package it.eparlato.kata.rpg.things;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThingTest {

    private Thing tree;

    @Before
    public void setUp() {
        tree = new Tree();
    }

    @Test
    public void has_health() {
        assertThat(tree.health(), is(Tree.MAX_TREE_HEALTH));
    }

    @Test
    public void can_receive_damage() {
        int damageDealt = 200;

        tree.receiveDamage(damageDealt);

        assertThat(tree.health(), is(Tree.MAX_TREE_HEALTH - damageDealt));
    }

    @Test
    public void has_health_zero_when_damage_exceeds_current_health() {
        tree.receiveDamage(Tree.MAX_TREE_HEALTH + 1);

        assertThat(tree.health(), is(0));
    }

    @Test
    public void is_destroyed_when_health_is_zero() {
        tree.receiveDamage(Tree.MAX_TREE_HEALTH);

        assertThat(tree.status(), is(Status.DESTROYED));
    }

    @Test
    public void is_intact_when_health_is_above_zero() {
        tree.receiveDamage(Tree.MAX_TREE_HEALTH - 1);

        assertThat(tree.status(), is(Status.INTACT));
    }
}