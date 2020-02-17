package it.eparlato.kata.rpg.things;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThingTest {

    @Test
    public void has_health() {
        int MAX_TREE_HEALTH = 2000;
        Thing tree = new Tree(MAX_TREE_HEALTH);

        assertThat(tree.health(), is(MAX_TREE_HEALTH));
    }
}