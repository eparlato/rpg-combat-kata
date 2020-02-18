package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.AttackRules;
import it.eparlato.kata.rpg.things.Thing;
import it.eparlato.kata.rpg.things.Tree;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackTest {
    private AttackRules attackRules = mock(AttackRules.class);

    @Test
    public void has_no_effect_if_attacker_and_target_are_allies() {
        Character attacker = new Character();
        Character target = new Character();

        when(attackRules.areAllies(attacker, target)).thenReturn(true);

        Attack attack = new Attack(attacker,5, attackRules);
        attack.on(target);

        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }

    @Test
    public void can_occur_on_a_thing() {
        int damageEffort = 15;
        Thing target = new Tree();

        Attack attack = new Attack(new Character(), damageEffort, attackRules);
        attack.on(target);

        Assert.assertThat(target.health(), is(Tree.MAX_TREE_HEALTH - damageEffort));
    }
}