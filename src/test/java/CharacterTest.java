import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CharacterTest {

    public static final int DAMAGE_EXCEEDING_HEALTH = Character.INITIAL_HEALTH + 1;
    private Character character;

    @Before
    public void setUp() {
        character = new Character();
    }

    @Test
    public void has_health_at_1000_when_created() {
        assertThat(character.health(), is(Character.INITIAL_HEALTH));
    }

    @Test
    public void loses_health_when_receiving_damage() {
        int currentHealth = character.health();

        character.receiveDamage(1);

        assertThat(character.health(), is(currentHealth-1));
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        character.receiveDamage(DAMAGE_EXCEEDING_HEALTH);

        assertThat(character.health(), is(0));
    }
}

class Character {
    public static final int INITIAL_HEALTH = 1000;
    private int health;

    public Character() {
        this.health = INITIAL_HEALTH;
    }

    public int health() {
        return this.health;
    }

    public void receiveDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }
}