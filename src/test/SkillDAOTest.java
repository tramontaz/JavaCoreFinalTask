package test;

import dao.JavaIOSkillDAOImpl;
import model.Developer;
import model.Skill;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class SkillDAOTest {
    public static void main(String[] args) {
        Skill javaCore = new Skill(1, "Java core");
        Skill sql = new Skill(2, "SQL");
        Skill git = new Skill(3, "GIT");
        Skill maven = new Skill(4, "Maven");
        Skill hibernate = new Skill(5, "Hibernate");
        Skill spring = new Skill(6, "Spring");
        Skill html = new Skill(7, "HTML");

        Set<Skill> skills = new HashSet<>();
        skills.add(javaCore);
        skills.add(sql);
        skills.add(git);


        Developer developer = new Developer(1, "Ivan", "Ivanov", "Java Junior Developer",
                skills, new BigDecimal(800));

        JavaIOSkillDAOImpl javaIOSkillDAO = new JavaIOSkillDAOImpl("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/skills.txt");

        javaIOSkillDAO.save(maven);
        javaIOSkillDAO.save(javaCore);
        javaIOSkillDAO.save(sql);

        System.out.println("\n=============================================\n");


        System.out.println(javaIOSkillDAO.getById(2)); //get sql skill

        System.out.println("\n=============================================\n");
        for (Skill skill : javaIOSkillDAO.getAll()) {  //get all skills
            System.out.println(skill);
        }


        System.out.println("\n=============================================\n");
        javaIOSkillDAO.save(git);
        javaIOSkillDAO.save(hibernate);
        javaIOSkillDAO.save(spring);
        javaIOSkillDAO.save(html);
        for (Skill skill : javaIOSkillDAO.getAll()) {  //get all skills
            System.out.println(skill);
        }


        System.out.println("\n=============================================\n");
//        javaIOSkillDAO.delete(3); //delete git
        for (Skill skill : javaIOSkillDAO.getAll()) {  //get all skills
            System.out.println(skill);
        }
    }
}