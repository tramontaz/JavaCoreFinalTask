import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Project implements HaveID {
    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String name;
    private Set<HaveID> teams;

    public Project(String name) {
        id = aLong.incrementAndGet();
        this.name = name;
        teams = new HashSet<>();
    }

    public Project(long id, String name, Set<HaveID> teams) {
        this.id = id;
        this.name = name;
        this.teams = teams;
    }

    private StringBuilder getTeamsFromSet() {
        StringBuilder teamsToString = new StringBuilder("{");
        for (HaveID team : teams) teamsToString.append(team.getId() + ",");
        teamsToString.deleteCharAt(teamsToString.length() - 1);
        teamsToString.append("}");
        return teamsToString;
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

    @Override
    public StringBuilder getInfo() {
        return new StringBuilder(getId() + ","
                + getName() + "," + getTeamsFromSet() + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HaveID> getTeams() {
        return teams;
    }
}
