package model;

import java.util.Set;

public class Developer {
    private Set<Skill> set;

    public Developer(Set<Skill> set) {
        this.set = set;
    }

    public Set<Skill> getSet() {
        return set;
    }

    public void setSet(Set<Skill> set) {
        this.set = set;
    }
}
