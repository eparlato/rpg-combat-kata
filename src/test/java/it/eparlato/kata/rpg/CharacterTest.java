package it.eparlato.kata.rpg;

import it.eparlato.kata.rpg.actions.Action;
import it.eparlato.kata.rpg.actions.Attack;
import it.eparlato.kata.rpg.actions.Healing;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static it.eparlato.kata.rpg.Character.MAX_HEALTH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CharacterTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = MAX_HEALTH + 1;
    private Character character;

    @Before
    public void setUp() {
        character = new Character();
    }

    @Test
    public void has_health_at_1000_when_created() {
        assertThat(character.health(), is(MAX_HEALTH));
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

        heal(character, 1);

        assertThat(character.health(), is(previousHealth + 1));
    }

    @Test
    public void healing_cannot_raise_health_above_max_health() {
        attack(character, 1);

        heal(character, 2);

        assertThat(character.health(), is(MAX_HEALTH));
    }

    @Test
    public void damage_is_reduced_by_50_percent_when_target_level_is_at_least_five_levels_above_attacker() {
        Character attacker = new Character(attackerLevel = 1);
        Character target = new Character(attackerLevel + Character.DAMAGE_REDUCTION_THRESHOLD);

        attack(attacker, target, damage = 2);

        assertThat(target.health(), is(MAX_HEALTH - fiftyPercentOfReductionOf(damage)));
    }

    @Test
    public void reduced_by_50_percent_damage_is_rounded_down_when_damage_is_an_odd_value() {
        Character attacker = new Character(attackerLevel = 1);
        Character target = new Character(attackerLevel + Character.DAMAGE_REDUCTION_THRESHOLD);

        attack(attacker, target, damage = 3);

        int damageReceivedByTarget = 1;

        assertThat(target.health(), is(MAX_HEALTH - damageReceivedByTarget));
    }

    @Test
    public void damage_is_increased_by_50_percent_when_attacker_level_is_at_least_five_levels_above_target() {
        Character target = new Character(targetLevel = 1);

        Character attacker = new Character(targetLevel + Character.DAMAGE_AMPLIFICATION_THRESHOLD);

        attack(attacker, target, damage = 2);

        assertThat(target.health(), is(MAX_HEALTH - fiftyPercentOfAmplificationOf(damage)));
    }

    @Test
    public void character_has_an_attack_max_range() {
        assertThat(character.maxRange(), is(1));
    }

    @Test
    public void a_character_can_not_deal_damage_to_a_target_if_target_has_higher_range() {
        RangedFighter rangedFighter = new RangedFighter();
        Character meleeFighter = new MeleeFighter();

        attack(meleeFighter, rangedFighter, 1);

        assertThat(rangedFighter.health(), is(MAX_HEALTH));
    }

    @Test
    public void character_can_join_factions() {
        Set<FactionEnum> factions = aSetOf(FactionEnum.DEVELOPERS, FactionEnum.SYSOPS);

        join(FactionEnum.DEVELOPERS);
        join(FactionEnum.SYSOPS);

        assertThat(character.factionsJoined(), is(factions));
    }

    @Test
    public void newly_create_character_does_not_belong_to_any_faction() {
        assertThat(character.factionsJoined(), is(Collections.emptySet()));
    }

    @Test
    public void character_can_leave_a_faction() {
        join(FactionEnum.DEVELOPERS);
        join(FactionEnum.SYSOPS);

        leave(FactionEnum.DEVELOPERS);

        assertThat(character.factionsJoined(), is(aSetOf(FactionEnum.SYSOPS)));
    }

    @Test
    public void character_can_not_join_the_same_faction_twice() {
        join(FactionEnum.DEVELOPERS);
        join(FactionEnum.SYSOPS);
        join(FactionEnum.DEVELOPERS);

        assertThat(character.factionsJoined(), is(aSetOf(FactionEnum.SYSOPS, FactionEnum.DEVELOPERS)));
    }

    @Test
    public void character_is_allied_with_another_character_if_they_belong_to_the_same_faction() {
        Character meleeFighter = new MeleeFighter();

        join(FactionEnum.DEVELOPERS);
        join(FactionEnum.SYSOPS);
        join(meleeFighter, FactionEnum.DEVELOPERS);

        assertThat(character.isAlliedWith(meleeFighter), is(true));
    }

    @Test
    public void character_cannot_deal_damage_to_an_ally() {
        Character ally = new Character();

        join(FactionEnum.DEVELOPERS);
        join(ally, FactionEnum.DEVELOPERS);

        attack(character, ally, 10);

        assertThat(ally.health(), is(MAX_HEALTH));
    }

    @Test
    public void character_can_heal_an_ally() {
        Character ally = new Character();

        join(FactionEnum.SYSOPS);
        join(ally, FactionEnum.SYSOPS);

        int inflictedDamage = 10;
        int healing = 5;

        attack(ally, inflictedDamage);

        heal(character, ally, healing, true);

        assertThat(ally.health(), is(MAX_HEALTH - inflictedDamage + healing));
    }

    @Test
    public void character_can_not_heal_a_not_allied_character() {
        Character notAllied = new Character();

        int inflictedDamage = 10;
        int healing = 5;

        attack(notAllied, inflictedDamage);

        heal(character, notAllied, healing, false);

        assertThat(notAllied.health(), is(MAX_HEALTH - inflictedDamage));
    }

    private HashSet<FactionEnum> aSetOf(FactionEnum... factions) {
        return new HashSet<>(Arrays.asList(factions));
    }

    private int fiftyPercentOfReductionOf(int damage) {
        return (int) (damage * 0.5);
    }

    private int fiftyPercentOfAmplificationOf(int damage) {
        return (int) (damage * 1.5);
    }

    private void attack(Character target, int damageDealt) {
        attack(new Character(), target, damageDealt);
    }

    private void attack(Character attacker, Character target, int damageDealt) {
        Action attack = new Attack(attacker, target, damageDealt, false);
        attack.execute();
    }

    private void heal(Character patient, int healingQuantity) {
        heal(patient, patient, healingQuantity, false);
    }

    private void heal(Character healer, Character patient, int healingQuantity, boolean areAllies) {
        Action healing = new Healing(healer, patient, healingQuantity, areAllies);
        healing.execute();
    }

    private void join(FactionEnum faction) {
        join(character, faction);
    }

    private void join(Character character, FactionEnum faction) {
        character.join(faction);
    }

    private void leave(FactionEnum faction) {
        character.leave(faction);
    }

    private int attackerLevel;
    private int damage;
    private int targetLevel;
}

