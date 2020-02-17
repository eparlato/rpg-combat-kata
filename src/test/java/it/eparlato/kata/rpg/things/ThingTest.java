package it.eparlato.kata.rpg.things;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThingTest {

    private Thing tree;

    @Before
    public void setUp() throws Exception {
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
}