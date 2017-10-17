import dao.JavaIOSkillDAOImpl;
import model.Developer;
import model.Skill;

import java.util.HashSet;
import java.util.Set;

public class SkillDAOTest {
    public static void main(String[] args) {
        Skill javaCore = new Skill(1, "Java core");
        Skill sql = new Skill(2, "SQL");
        Skill git = new Skill(3, "GIT");
        Skill maven = new Skill(4, "Maven");

        Set<Skill> skills = new HashSet<>();
        skills.add(javaCore);
        skills.add(sql);
        skills.add(git);


        Developer developer = new Developer(skills);

        JavaIOSkillDAOImpl javaIOSkillDAO = new JavaIOSkillDAOImpl("skills.txt", developer, maven);

        javaIOSkillDAO.save(maven);
        javaIOSkillDAO.save(javaCore);
        javaIOSkillDAO.save(sql);

        System.out.println("\n=============================================\n");


        System.out.println(javaIOSkillDAO.getById(2L)); //get sql skill

        System.out.println("\n=============================================\n");
        for (Skill skill : javaIOSkillDAO.getAll()) {  //get all skills
            System.out.println(skill);
        }


        System.out.println("\n=============================================\n");
        javaIOSkillDAO.save(git);
        for (Skill skill : javaIOSkillDAO.getAll()) {  //get all skills
            System.out.println(skill);
        }


        System.out.println("\n=============================================\n");
        javaIOSkillDAO.delete(3); //delete git
        for (Skill skill : javaIOSkillDAO.getAll()) {  //get all skills
            System.out.println(skill);
        }
    }
}