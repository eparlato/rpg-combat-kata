package it.eparlato.kata.rpg;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CharacterTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = Character.MAX_HEALTH + 1;
    private Character character;

    @Before
    public void setUp() {
        character = new Character();
    }

    @Test
    public void has_health_at_1000_when_created() {
        assertThat(character.health(), is(Character.MAX_HEALTH));
    }

    @Test
    public void a_character_can_deal_damage_to_another_character() {
        int currentHealth = character.health();

        attack(character, 1);

        assertThat(character.health(), is(currentHealth - 1));
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        attack(character, DAMAGE_EXCEEDING_HEALTH);

        assertThat(character.health(), is(0));
    }

    @Test
    public void character_dies_when_health_becomes_zero() {
        attack(character, DAMAGE_EXCEEDING_HEALTH);

        assertThat(character.isAlive(), is(false));
    }

    @Test
    public void character_is_alive_when_created() {
        assertThat(character.isAlive(), is(true));
    }

    @Test
    public void character_can_be_healed() {
        attack(character, 2);
        int previousHealth = character.health();

        character.heal(1);

        assertThat(character.health(), is(previousHealth + 1));
    }

    @Test
    public void healing_cannot_raise_health_above_max_health() {
        attack(character, 1);

        character.heal(2);

        assertThat(character.health(), is(Character.MAX_HEALTH));
    }

    @Test
    public void damage_is_reduced_by_50_percent_when_target_level_is_at_least_five_levels_above_attacker() {
        Character attacker = new Character(attackerLevel = 1);
        Character target = new Character(attackerLevel + Character.DAMAGE_REDUCTION_THRESHOLD);

        attacker.attack(target, damage = 2);

        assertThat(target.health(), is(Character.MAX_HEALTH - fiftyPercentOfReductionOf(damage)));
    }

    @Test
    public void reduced_by_50_percent_damage_is_rounded_down_when_damage_is_an_odd_value() {
        Character attacker = new Character(attackerLevel = 1);
        Character target = new Character(attackerLevel + Character.DAMAGE_REDUCTION_THRESHOLD);

        attacker.attack(target, damage = 3);

        int damageReceivedByTarget = 1;

        assertThat(target.health(), is(Character.MAX_HEALTH - damageReceivedByTarget));
    }

    @Test
    public void damage_is_increased_by_50_percent_when_attacker_level_is_at_least_five_levels_above_target() {
        Character target = new Character(targetLevel = 1);

        Character attacker = new Character(targetLevel + Character.DAMAGE_AMPLIFICATION_THRESHOLD);
        attacker.attack(target, damage = 2);

        assertThat(target.health(), is(Character.MAX_HEALTH - fiftyPercentOfAmplificationOf(damage)));
    }

    @Test
    public void character_has_an_attack_max_range() {
        assertThat(character.maxRange(), is(1));
    }

    @Test
    public void character_can_join_factions() {
        List<Faction> factions = Arrays.asList(Faction.DEVELOPERS, Faction.SYSOPS);

        character.join(Faction.DEVELOPERS);
        character.join(Faction.SYSOPS);

        assertThat(character.factionsJoined(), is(factions));
    }

    @Test
    public void a_character_can_not_deal_damage_to_a_target_if_target_has_higher_range() {
        RangedFighter rangedFighter = new RangedFighter();
        Character meleeFighter = new MeleeFighter();

        meleeFighter.attack(rangedFighter, 1);

        assertThat(rangedFighter.health(), is(Character.MAX_HEALTH));
    }

    private int fiftyPercentOfReductionOf(int damage) {
        return (int) (damage * 0.5);
    }

    private int fiftyPercentOfAmplificationOf(int damage) {
        return (int) (damage * 1.5);
    }

    private void attack(Character target, int damageDealt) {
        new Character().attack(target, damageDealt);
    }

    private int attackerLevel;
    private int damage;
    private int targetLevel;
}
