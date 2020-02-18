package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import it.eparlato.kata.rpg.AttackRules;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HealingTest {

    private static final Character DOES_NOT_CARE_WHO_HE_IS = new Character();
    private Character target;

    private AttackRules attackRules = mock(AttackRules.class);

    @Before
    public void setUp() {
        target = new Character();
    }

    @Test
    public void has_effect_if_two_characters_are_allies() {
        when(attackRules.areAllies(DOES_NOT_CARE_WHO_HE_IS, target)).thenReturn(true);

        int damageEffort = 10;
        attackTargetWithDamageEffortOf(damageEffort, attackRules);

        int healingQuantity = 10;
        healTargetWithHealingQuantityOf(healingQuantity, attackRules);

        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }

    @Test
    public void has_no_effect_if_two_characters_are_not_allies() {
        when(attackRules.areAllies(DOES_NOT_CARE_WHO_HE_IS, target)).thenReturn(false);

        int damageEffort = 10;
        attackTargetWithDamageEffortOf(damageEffort, attackRules);

        healTargetWithHealingQuantityOf(5, attackRules);

        assertThat(target.health(), Is.is(Character.MAX_HEALTH - damageEffort));
    }

    private void attackTargetWithDamageEffortOf(int damageEffort, AttackRules attackRules) {
        Attack attack = new Attack(DOES_NOT_CARE_WHO_HE_IS, damageEffort, attackRules);
        attack.on(target);
    }

    private void healTargetWithHealingQuantityOf(int healingQuantity, AttackRules attackRules) {
        Healing healing = new Healing(DOES_NOT_CARE_WHO_HE_IS, healingQuantity, attackRules);
        healing.on(target);
    }
}