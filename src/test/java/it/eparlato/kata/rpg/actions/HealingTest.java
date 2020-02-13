package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class HealingTest {

    private static final Character DOES_NOT_CARE_WHO_HE_IS = new Character();
    private static final boolean THEY_ARE_ALLIES = true;
    private static final boolean THEY_ARE_NOT_ALLIES = false;
    private Character target;

    @Before
    public void setUp() {
        target = new Character();
    }

    @Test
    public void has_effect_if_two_characters_are_allies() {
        int damageEffort = 10;
        attackTargetWithDamageEffortOf(damageEffort);

        int healingQuantity = 10;
        healTargetWithHealingQuantityOf(healingQuantity, THEY_ARE_ALLIES);

        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }

    @Test
    public void has_no_effect_if_two_characters_are_not_allies() {
        int damageEffort = 10;
        attackTargetWithDamageEffortOf(damageEffort);

        healTargetWithHealingQuantityOf(5, THEY_ARE_NOT_ALLIES);

        assertThat(target.health(), Is.is(Character.MAX_HEALTH - damageEffort));
    }

    private void attackTargetWithDamageEffortOf(int damageEffort) {
        Attack attack = new Attack(DOES_NOT_CARE_WHO_HE_IS, target, damageEffort, THEY_ARE_NOT_ALLIES);
        attack.execute();
    }

    private void healTargetWithHealingQuantityOf(int healingQuantity, boolean areAllies) {
        Healing healing = new Healing(DOES_NOT_CARE_WHO_HE_IS, target, healingQuantity, areAllies);
        healing.execute();
    }
}