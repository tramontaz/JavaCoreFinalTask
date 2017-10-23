package model;

import java.util.Set;

public class Team {
    private Set<Developer> set;

    public Team(Set<Developer> set) {
        this.set = set;
    }

    public Set<Developer> getSet() {
        return set;
    }

    public void setSet(Set<Developer> set) {
        this.set = set;
    }
}
