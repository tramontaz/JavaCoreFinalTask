package dao;

import model.Customer;

import java.util.Set;

public interface CustomerDAO {
    void save(Customer team); //create
    void update(Customer team); //update
    void delete(long id); //delete
    Customer getById(long id);
    Set<Customer> getAll();
}
