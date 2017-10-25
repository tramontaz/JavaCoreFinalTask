package model;

import java.util.Set;

public class Team {
    private long id;
    private String name;
    private Set<Developer> set;


    public Team(long id, String name, Set<Developer> set) {
        this.id = id;
        this.name = name;
        this.set = set;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Developer> getSet() {
        return set;
    }

    public void setSet(Set<Developer> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "id=" + id +
                "\nname=" + name +
                "\nDevelopers set=" + set;
    }
}
