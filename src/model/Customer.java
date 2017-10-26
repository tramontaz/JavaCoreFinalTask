package model;

import java.util.Set;

public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Project> set;

    public Customer(long id, String firstName, String lastName, String address, Set<Project> set) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.set = set;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Project> getSet() {
        return set;
    }

    public void setSet(Set<Project> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", set=" + set +
                '}';
    }
}
