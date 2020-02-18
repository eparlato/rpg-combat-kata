package it.eparlato.kata.rpg;

import it.eparlato.kata.rpg.actions.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FactionTest {
    private Character character;
    private Faction developers;
    private Faction sysOps;

    @Before
    public void setUp() {
        character = new Character();
        developers = new Faction();
        sysOps = new Faction();
    }

    @Test
    public void can_accept_a_new_member() {
        join(developers);
        join(sysOps);

        assertThat(developers.hasMember(character), is(true));
        assertThat(sysOps.hasMember(character), is(true));
    }

    @Test
    public void can_throw_out_a_member() {
        join(developers);
        leave(developers);

        assertThat(developers.hasMember(character), is(false));
    }

    @Test
    public void tells_if_two_characters_are_allied() {
        Character alliedFellow = new Character();

        join(sysOps);
        join(sysOps, alliedFellow);

        assertThat(sysOps.areAllies(character, alliedFellow), is(true));
    }

    private void join(Faction faction) {
        join(faction, character);
    }

    private void join(Faction faction, Character character) {
        Action accept = new Accept(faction);
        accept.on(character);
    }

    private void leave(Faction faction) {
        //_Action throwOut = new _ThrowOut(faction, character);
        //throwOut.execute();
        Action throwOut = new ThrowOut(faction);
        throwOut.on(character);
    }
}