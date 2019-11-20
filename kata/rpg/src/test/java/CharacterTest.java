import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CharacterTest {
    @Test
    public void has_health_at_1000_when_created() {
        Character character = new Character();

        assertThat(character.health(), is(Character.INITIAL_HEALTH));
    }

    @Test
    public void loses_health_when_receiving_damage() {
        Character character = new Character();

        int currentHealth = character.health();

        character.receiveDamage(1);

        assertThat(character.health(), is(currentHealth-1));
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
    }
}
