package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Faction;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

public class AttackTest {

    public static final boolean THEY_ARE_ALLIES = true;

    @Test
    public void has_no_effect_if_attacker_and_target_are_allies() {
        Character attacker = new Character();
        Character target = new Character();

        Attack attack = new Attack(attacker, target, 5, THEY_ARE_ALLIES);
        attack.execute();

        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }
}