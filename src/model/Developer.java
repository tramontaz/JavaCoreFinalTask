package model;

import java.math.BigDecimal;
import java.util.Set;

public class Developer {
    private long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private Set<Skill> set;
    private BigDecimal salary;

    public Developer(long id, String firstName, String lastName, String specialty, Set<Skill> set, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.set = set;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Set<Skill> getSet() {
        return set;
    }

    public void setSet(Set<Skill> set) {
        this.set = set;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer: " +
                "\nid=" + id +
                "\nfirstName='" + firstName +
                "\nlastName='" + lastName +
                "\nspecialty='" + specialty +
                "\nSkill set=" + set +
                "\nsalary=" + salary;
    }
}
