package it.eparlato.kata.rpg;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RangedFighterTest {
    @Test
    public void a_ranged_fighter_has_an_attack_max_range_of_20() {
        Character rangedFighter = new RangedFighter();

        assertThat(rangedFighter.maxRange(), is(20));
    }
}