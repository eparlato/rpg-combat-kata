package it.eparlato.kata.rpg;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackRulesTest {
    AttackRules attackRules;

    Faction faction = mock(Faction.class);

    @Test
    public void can_see_whether_two_characters_are_allies_or_not() {
        Character fellow = new Character();
        Character fellowAlly = new Character();
        createFactionTwoAlliedFellows(fellow, fellowAlly);

        assertThat(attackRules.areAllies(fellow, fellowAlly), is(true));
    }

    private void createFactionTwoAlliedFellows(Character fellow, Character fellowAlly) {
        when(faction.areAllies(fellow, fellowAlly)).thenReturn(true);

        attackRules = new AttackRules(Sets.newSet(faction));
    }
}
