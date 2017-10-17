import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Customer implements HaveID {
    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String firstName;
    private String lastName;
    private String address;
    private Set<HaveID> projects;

    public Customer(String firstName, String lastName, String address) {
        id = aLong.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        projects = new HashSet<>();
    }

    public Customer(long id, String firstName, String lastName, String address, Set<HaveID> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.projects = projects;
    }

    private StringBuilder getProjectsFromSet() {
        StringBuilder projectsToString = new StringBuilder("{");
        for (HaveID project : projects) projectsToString.append(project.getId() + ",");
        projectsToString.deleteCharAt(projectsToString.length() - 1);
        projectsToString.append("}");
        return projectsToString;
    }

    void addProjectToCustomer(Project project) {
        projects.add(project);
    }

    void removeProjectFromCustomer(Project project) {
        projects.remove(project);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<HaveID> getProjects() {
        return projects;
    }

    public long getId() {
        return id;
    }

    @Override
    public StringBuilder getInfo() {
        return new StringBuilder(getId() + "," + getFirstName() + ","
                + getLastName() + "," + getAddress() + getProjectsFromSet());
    }
}
