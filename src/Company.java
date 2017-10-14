import java.util.HashSet;
import java.util.Set;

public class Company implements HaveID {
    private long id;
    private String name;
    private Set<Project> projects;

    public Company(String name) {
        id++;
        this.name = name;
        projects = new HashSet<>();
    }

    void addProjectForCompany(Project project) {
        projects.add(project);
    }

    void removeProjectFromCompany(Project project) {
        projects.remove(project);
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

