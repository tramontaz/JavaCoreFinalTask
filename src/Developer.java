import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Developer implements HaveID {
    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private Set<Skill> skills;
    private BigDecimal salary;

    public Developer(String firstName, String lastName, String specialty, int salary) {
        id = aLong.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.salary = new BigDecimal(salary);
        skills = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }
}
