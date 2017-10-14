import java.util.HashSet;
import java.util.Set;

public class Team implements HaveID {

    private long id;
    private String name;
    private Set<Developer> developers;

    public Team(String name) {
        id++;
        this.name = name;
        this.developers = new HashSet<>();
    }

    void addDeveloperIntoTeam(Developer developer) {
        developers.add(developer);
    }

    void removeReveloperFromTeam(Developer developer) {
        developers.remove(developer);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setName(String name) {
        this.name = name;
    }
}
