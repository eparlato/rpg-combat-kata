import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CharacterTest {
    @Test
    public void has_health_at_1000_when_created() {
        Character character = new Character();

        assertThat(character.health(), is(Character.INITIAL_HEALTH));
    }
}

class Character {

    public static final int INITIAL_HEALTH = 1000;

    public int health() {
        return INITIAL_HEALTH;
    }
}
