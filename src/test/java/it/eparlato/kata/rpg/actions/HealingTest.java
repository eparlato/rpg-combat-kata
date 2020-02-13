package it.eparlato.kata.rpg.actions;

import it.eparlato.kata.rpg.Character;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class HealingTest {

    public static final Character DOES_NOT_CARE_WHO_HE_IS = new Character();
    public static final boolean THEY_ARE_ALLIES = true;
    public static final boolean THEY_ARE_NOT_ALLIES = false;

    @Test
    public void has_effect_if_two_characters_are_allies() {
        Character target = new Character();

        int damageEffort = 10;
        Attack attack = new Attack(DOES_NOT_CARE_WHO_HE_IS, target, damageEffort, THEY_ARE_NOT_ALLIES);
        attack.execute();

        int healingQuantity = 10;
        Healing healing = new Healing(DOES_NOT_CARE_WHO_HE_IS, target, healingQuantity, THEY_ARE_ALLIES);
        healing.execute();

        assertThat(target.health(), Is.is(Character.MAX_HEALTH));
    }

    @Test
    public void has_no_effect_if_two_characters_are_not_allies() {
        Character target = new Character();

        int damageEffort = 10;
        Attack attack = new Attack(DOES_NOT_CARE_WHO_HE_IS, target, damageEffort, THEY_ARE_NOT_ALLIES);
        attack.execute();

        int healingQuantity = 5;
        Healing healing = new Healing(DOES_NOT_CARE_WHO_HE_IS, target, healingQuantity, THEY_ARE_NOT_ALLIES);
        healing.execute();

        assertThat(target.health(), Is.is(Character.MAX_HEALTH - damageEffort));
    }
}