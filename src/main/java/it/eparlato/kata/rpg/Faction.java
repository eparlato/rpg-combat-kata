package it.eparlato.kata.rpg;

import java.util.HashSet;
import java.util.Set;

public class Faction {
    private Set<Character> members = new HashSet<>();

    public boolean hasMember(Character character) {
        return members.contains(character);
    }

    public void accept(Character member) {
        this.members.add(member);
    }

    public void throwOut(Character member) {
        this.members.remove(member);
    }
}
