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
    private Set<HaveID> skills;
    private BigDecimal salary;

    public Developer(String firstName, String lastName, String specialty, int salary) {
        id = aLong.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.salary = new BigDecimal(salary);
        skills = new HashSet<>();
    }

    public Developer(long id, String firstName, String lastName, String specialty, Set<HaveID> skills, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.skills = skills;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    private StringBuilder skillsFromSet() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (HaveID skill : skills) {
            stringBuilder.append(skill.getId() + "#");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}");
        return stringBuilder;
    }

    @Override
    public StringBuilder getInfo() {
        return new StringBuilder(getId() + "," + getFirstName() + ","
                + getLastName() + "," + getSpecialty() + "," + skillsFromSet() + "," + getSalary() + "\n");
    }

    private String getFirstName() {
        return firstName;
    }

    private String getLastName() {
        return lastName;
    }

    private String getSpecialty() {
        return specialty;
    }

    private BigDecimal getSalary() {
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

    public Set<HaveID> getSkills() {
        return skills;
    }

    public void setSkills(Set<HaveID> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) { skills.add(skill); }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }
}
