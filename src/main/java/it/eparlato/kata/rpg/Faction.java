package it.eparlato.kata.rpg;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Faction {
    private Set<Character> members = new HashSet<>();

    public Faction() {
    }

    public boolean hasMember(Character character) {
        return members.contains(character);
    }

    public void accept(Character member) {
        this.members.add(member);
    }

}
