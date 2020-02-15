package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.Council;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackTest {
    private Council council = mock(Council.class);

    @Test
    public void has_no_effect_if_attacker_and_target_are_allies() {
        Character attacker = new Character();
        Character target = new Character();

        when(council.areAllies(attacker, target)).thenReturn(true);

        Attack attack = new Attack(attacker, target, 5, council);
        attack.execute();

        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }
}