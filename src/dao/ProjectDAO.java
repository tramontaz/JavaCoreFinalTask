package dao;


import model.Project;

import java.util.Set;

public interface ProjectDAO {
    void save(Project team); //create
    void update(Project team); //update
    void delete(long id); //delete
    Project getById(long id);
    Set<Project> getAll();
}
