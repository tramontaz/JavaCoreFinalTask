package model;


import java.util.Set;

public class Company {
    private long id;
    private String name;
    private Set<Project> set;

    public Company(long id, String name, Set<Project> set) {
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

    public Set<Project> getSet() {
        return set;
    }

    public void setSet(Set<Project> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", set=" + set +
                '}';
    }
}
