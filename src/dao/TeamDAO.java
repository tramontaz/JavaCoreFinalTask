package dao;

import model.Team;

import java.util.Set;

public interface TeamDAO {
    void save(Team team); //create
    void update(Team team); //update
    void delete(long id); //delete
    Team getById(long id);
    Set<Team> getAll();
}
