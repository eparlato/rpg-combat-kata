package it.eparlato.kata.rpg;

import it.eparlato.kata.rpg.actions.Action;
import it.eparlato.kata.rpg.actions.Accept;
import it.eparlato.kata.rpg.actions.ThrowOut;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FactionTest {
    private Character character;

    @Before
    public void setUp() {
        character = new Character();
    }

    @Test
    public void can_accept_a_new_member() {
        Faction developers = new Faction();
        Faction sysOps = new Faction();

        join(developers);
        join(sysOps);

        assertThat(developers.hasMember(character), is(true));
        assertThat(sysOps.hasMember(character), is(true));
    }

    @Test
    public void can_throw_out_a_member() {
        Faction developers = new Faction();

        join(developers);
        leave(developers);

        assertThat(developers.hasMember(character), is(false));
    }

    private void leave(Faction faction) {
        Action throwOut = new ThrowOut(faction, character);
        throwOut.execute();
    }

    private void join(Faction faction) {
        Action accept = new Accept(faction, character);
        accept.execute();
    }
}