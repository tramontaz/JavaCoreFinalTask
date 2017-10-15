import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Company implements HaveID {
    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String name;
    private Set<Project> projects;

    public Company(String name) {
        id = aLong.incrementAndGet();
        this.name = name;
        projects = new HashSet<>();
    }

    public Company(long id, String name, Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }

    private StringBuilder getProjectsFromSet() {
        StringBuilder projectsToString = new StringBuilder("{");
        for (Project project : projects) projectsToString.append(project.getId() + ",");
        projectsToString.deleteCharAt(projectsToString.length() - 1);
        projectsToString.append("}");
        return projectsToString;
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

    @Override
    public StringBuilder getInfo() {
        return new StringBuilder(getId() + "," + getName() + ","
                + getProjectsFromSet());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }
}

