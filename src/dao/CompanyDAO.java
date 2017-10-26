package dao;

import model.Company;

import java.util.Set;

public interface CompanyDAO {
    void save(Company team); //create
    void update(Company team); //update
    void delete(long id); //delete
    Company getById(long id);
    Set<Company> getAll();
}
