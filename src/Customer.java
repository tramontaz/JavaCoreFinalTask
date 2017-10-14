import java.util.HashSet;
import java.util.Set;

public class Customer implements HaveID{
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Project> projects;

    public Customer(String firstName, String lastName, String address) {
        id++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        projects = new HashSet<>();
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

    public long getId() {
        return id;
    }
}
