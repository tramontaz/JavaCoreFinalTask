import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Team implements HaveID {

    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String name;
    private Set<Developer> developers;

    public Team(String name) {
        id = aLong.incrementAndGet();
        this.name = name;
        this.developers = new HashSet<>();
    }

    public Team(long id, String name, Set<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }

    private StringBuilder getDevelopersFromSet() {
        StringBuilder developersToString = new StringBuilder("{");
        for (Developer developer : developers) developersToString.append(developer.getId() + ",");
        developersToString.deleteCharAt(developersToString.length() - 1);
        developersToString.append("}");
        return developersToString;
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

    @Override
    public StringBuilder getInfo() {
        return new StringBuilder(getId() + "," + getName() + "," +
                getDevelopersFromSet() + "\n");
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
