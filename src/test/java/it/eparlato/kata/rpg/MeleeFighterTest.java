package it.eparlato.kata.rpg;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MeleeFighterTest {
    @Test
    public void a_melee_fighter_has_an_attack_range_of_2() {
        Character meleeFighter = new MeleeFighter();

        assertThat(meleeFighter.maxRange(), is(2));
    }
}