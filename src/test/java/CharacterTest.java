import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CharacterTest {

    public static final int DAMAGE_EXCEEDING_HEALTH = Character.MAX_HEALTH + 1;
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

    private int fiftyPercentOfReductionOf(int damage) {
        return (int) (damage * 0.5);
    }

    //TODO test: se faccio danno 1 viene ridotto a 0?

    @Test
    public void damage_is_increased_by_50_percent_when_attacker_level_is_at_least_five_levels_above_target() {
        Character target = new Character(targetLevel = 1);

        Character attacker = new Character(targetLevel + Character.DAMAGE_AMPLIFICATION_THRESHOLD);
        attacker.attack(target, damage = 2);

        assertThat(target.health(), is(Character.MAX_HEALTH - fiftyPercentOfAmplificationOf(damage)));
    }
    private int fiftyPercentOfAmplificationOf(int damage) {
        return (int) (damage * 1.5);
    }

    private void attack(Character target, int damageDealt) {
        new Character().attack(target, damageDealt);
    }

    int attackerLevel;
    int damage;
    int targetLevel;
}

class Character {
    public static final int MAX_HEALTH = 1000;
    public static final int DAMAGE_REDUCTION_THRESHOLD = 5;
    public static final int DAMAGE_AMPLIFICATION_THRESHOLD = 5;
    private int health;
    private int level;

    public Character() {
        this(1);
    }

    public Character(int level) {
        this.level = level;
        this.health = MAX_HEALTH;
    }

    public int health() {
        return this.health;
    }

    public void attack(Character target, int damage) {
        target.receiveAttackFrom(this, damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int healing) {
        health += healing;

        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    private void receiveAttackFrom(Character attacker, int damageEffort) {
        health -= computeDealtDamage(attacker, damageEffort);

        if (health < 0) {
            health = 0;
        }
    }

    private int computeDealtDamage(Character attacker, int damage) {
        if (shouldReduceReceivedDamageFrom(attacker)) {
            return halfOf(damage);
        }
        if (shouldIncreaseReceivedDamageFrom(attacker)) {
            return damage + halfOf(damage);
        }
        return damage;
    }

    private int halfOf(int damage) {
        return damage / 2;
    }

    private boolean shouldIncreaseReceivedDamageFrom(Character attacker) {
        return (attacker.level - level) >= DAMAGE_AMPLIFICATION_THRESHOLD;
    }

    private boolean shouldReduceReceivedDamageFrom(Character attacker) {
        return (level - attacker.level) >= DAMAGE_REDUCTION_THRESHOLD;
    }
}
