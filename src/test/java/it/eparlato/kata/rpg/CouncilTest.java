package it.eparlato.kata.rpg;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CouncilTest {
    Council council;

    @Test
    public void can_see_whether_two_characters_are_allies_or_not() {
        Character fellow = new Character();
        Character fellowAlly = new Character();
        createFactionTwoAlliedFellows(fellow, fellowAlly);

        assertThat(council.areAllies(fellow, fellowAlly), is(true));
    }

    private void createFactionTwoAlliedFellows(Character fellow, Character fellowAlly) {
        // TODO: complex test setup, I could avoid it using a mock of Faction, mocking its areAllies method...
        Set<Faction> factions = new HashSet<>();
        Faction warriors = new Faction();
        Faction wizards = new Faction();
        factions.add(warriors);
        factions.add(wizards);

        warriors.accept(fellow);
        warriors.accept(fellowAlly);

        council = new Council(factions);
    }
}
