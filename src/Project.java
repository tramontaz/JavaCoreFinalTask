import java.util.HashSet;
import java.util.Set;

public class Project implements HaveID {
    private long id;
    private String name;
    private Set<Team> teams;

    public Project(String name) {
        id++;
        this.name = name;
        teams = new HashSet<>();
    }

    void addTeamIntoProject(Team team) {
        teams.add(team);
    }

    void removeTeamFromProject(Team team) {
        teams.remove(team);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
