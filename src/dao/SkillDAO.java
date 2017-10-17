package dao;

import model.Skill;

import java.io.IOException;
import java.util.Set;

public interface SkillDAO {
    void save(Skill skill); //create
    void update(Skill skill);
    void delete(long id) throws IOException;
    Skill getById(long id);
    Set<Skill> getAll();
}
