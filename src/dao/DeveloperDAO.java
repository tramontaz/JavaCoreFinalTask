package dao;

import model.Developer;

import java.util.Set;

public interface DeveloperDAO {
    void save(Developer developer); //create
    void update(Developer developer); //update
    void delete(long id); //delete
    Developer getById(long id);
    Set<Developer> getAll();
}
