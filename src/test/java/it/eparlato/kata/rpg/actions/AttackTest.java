package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Faction;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

public class AttackTest {
    @Test
    public void has_no_effect_if_attacker_and_target_are_allies() {
        Character attacker = new Character();
        Character target = new Character();

        Faction managers = new Faction();
        managers.accept(attacker);
        managers.accept(target);

        Attack attack = new Attack(attacker, target, 5, Arrays.asList(managers));
        attack.execute();


        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }
}