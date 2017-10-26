package model;

import java.util.Set;

public class Project {
    private long id;
    private String name;
    private Set<Team> set;

    public Project(long id, String name, Set<Team> set) {
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

    public Set<Team> getSet() {
        return set;
    }

    public void setSet(Set<Team> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", set=" + set +
                '}';
    }
}
