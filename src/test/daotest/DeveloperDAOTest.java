package test.daotest;

import dao.JavaIODeveloperDAOImpl;
import model.Developer;
import model.Skill;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DeveloperDAOTest {
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

        Set<Skill> skillsOfPetr = new HashSet<>();
        skillsOfPetr.add(javaCore);
        skillsOfPetr.add(sql);
        skillsOfPetr.add(git);
        skillsOfPetr.add(maven);
        skillsOfPetr.add(hibernate);
        skillsOfPetr.add(spring);

        Set<Skill> skillsOfKlim = new HashSet<>();
        skillsOfKlim.add(javaCore);
        skillsOfKlim.add(sql);
        skillsOfKlim.add(git);
        skillsOfKlim.add(maven);
        skillsOfKlim.add(hibernate);
        skillsOfKlim.add(spring);
        skillsOfKlim.add(html);




        Developer developer = new Developer(1, "Ivan", "Ivanov", "Java Junior Developer",
                skills, new BigDecimal(800));

        Developer petr = new Developer(2, "Petr", "Petrov", "Java Middle Developer",
                skillsOfPetr, new BigDecimal(1800));

        Developer klim = new Developer(3, "Klim", "Avakov", "Java Middle-sec-grade Developer",
                skillsOfKlim, new BigDecimal(1950));
        Developer john = new Developer(4, "John", "Weak", "Java Middle Developer",
                skillsOfPetr, new BigDecimal(1800));

        Developer mary = new Developer(5, "Mary", "Key", "Java Middle-sec-grade Developer",
                skillsOfKlim, new BigDecimal(1950));

        System.out.println(developer.toString());
        System.out.println();


        JavaIODeveloperDAOImpl javaIODeveloperDAO = new JavaIODeveloperDAOImpl("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/developers.txt");

        javaIODeveloperDAO.save(developer);


        System.out.println("\n===============================================================================\n");

        developer.setFirstName("Konchita");
        javaIODeveloperDAO.update(developer);
        System.out.println(developer.toString());

        System.out.println("\n===============================================================================\n");

        javaIODeveloperDAO.save(petr);
        javaIODeveloperDAO.save(klim);
        javaIODeveloperDAO.save(john);
        javaIODeveloperDAO.save(mary);
        javaIODeveloperDAO.delete(1);

        System.out.println("Developer with id = 3:\n");
        Developer restoredDeveloper = javaIODeveloperDAO.getById(3);
        System.out.println(restoredDeveloper);

        System.out.println("\n===============================================================================\n");

        System.out.println("All developers:");

        for (Developer developer1 : javaIODeveloperDAO.getAll()) {
            System.out.println(developer1);
        }

        javaIODeveloperDAO.save(petr);
        javaIODeveloperDAO.save(klim);
        javaIODeveloperDAO.save(john);
        javaIODeveloperDAO.save(mary);
        javaIODeveloperDAO.save(developer);

    }
}
