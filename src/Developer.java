import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.lang.Long.*;


public class Developer implements HaveID {
    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private Set<Skill> skills;
    private BigDecimal salary;

    Long

    public Developer(String firstName, String lastName, String specialty, int salary) {
        id = aLong.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.salary = new BigDecimal(salary);
        skills = new HashSet<>();
    }

    public Developer(long id, String firstName, String lastName, String specialty, Set<Skill> skills, BigDecimal salary) {
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

    private StringBuilder getSkillsFromSet() {
        StringBuilder skillsToString = new StringBuilder("{");
        for (Skill skill : skills) skillsToString.append(skill.getId() + ",");
        skillsToString.deleteCharAt(skillsToString.length() - 1);
        skillsToString.append("}");
        return skillsToString;
    }

    private StringBuilder skillsFromSet() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Skill skill : skills) {
            stringBuilder.append(skill.getId() + "#");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}");
        return stringBuilder;
    }

    private Set<Skill> restoreSkills(HashSet<HaveID> standards){
        char open = '{';
        char close = '}';
        String substring = getInfo().substring((int) open, (int) close);
        String[] stringSkills = substring.split("#");
        Set<Skill> restoredSkills = new HashSet<>();
        int x = 0;
        for (HaveID standart : standards) {
            if (standart.equals(Long.parseLong(stringSkills[x]))) {
                restoredSkills.add((Skill) standart);
            }
        } return restoredSkills;
    }



    @Override
    public StringBuilder getInfo() {
        return new StringBuilder(getId() + "," + getFirstName() + ","
                + getLastName() + "," + getSpecialty() + "," + skillsFromSet() + "," + getSalary() + "\n");
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

    public void addSkill(Skill skill) { skills.add(skill); }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }
}
