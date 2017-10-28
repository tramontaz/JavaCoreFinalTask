package test.daotest;

import dao.JavaIOProjectDAOImpl;
import model.Developer;
import model.Project;
import model.Skill;
import model.Team;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProjectDAOTest {
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


        Set<Developer> rusTeam = new HashSet<>();
        rusTeam.add(developer);
        rusTeam.add(petr);
        rusTeam.add(klim);

        Set<Developer> usTeam = new HashSet<>();
        usTeam.add(john);
        usTeam.add(mary);


        Team firstTeam = new Team(1, "First Team", rusTeam);
        Team secondTeam = new Team(2, "Second Team", usTeam);
        Team thirdTeam = new Team(3, "Third Team", rusTeam);
        Team fourthTeam = new Team(4, "Fourth Team", usTeam);
        Team fifthTeam = new Team(5, "Fifth Team", rusTeam);
        Team sixthTeam = new Team(6, "Sixth Team", usTeam);

        Set<Team> cheapTeams = new HashSet<>();
        cheapTeams.add(fifthTeam);
        cheapTeams.add(thirdTeam);
        cheapTeams.add(sixthTeam);

        Set<Team> expensiveTeams = new HashSet<>();
        expensiveTeams.add(secondTeam);
        expensiveTeams.add(fourthTeam);
        expensiveTeams.add(fifthTeam);


        Project siteProject = new Project(1, "Site project", cheapTeams);
        Project bankingProject = new Project(2, "Banking project", expensiveTeams);


        JavaIOProjectDAOImpl javaIOProjectDAO = new JavaIOProjectDAOImpl("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/projects.txt");

        System.out.println(siteProject);
        System.out.println();
        System.out.println(bankingProject);

        System.out.println("\n========================================================================================\n");

        javaIOProjectDAO.save(siteProject);
        javaIOProjectDAO.save(bankingProject);

        System.out.println("\n========================================================================================\n");

        siteProject.setName("Visit site project");
        cheapTeams.remove(fifthTeam);
        siteProject.setSet(cheapTeams);
        System.out.println("Site project had changed: ");
        System.out.println(siteProject);
        javaIOProjectDAO.update(siteProject);

        System.out.println("\n========================================================================================\n");

//        javaIOProjectDAO.delete(1);

        System.out.println("\n========================================================================================\n");

        Project restoredProject = javaIOProjectDAO.getById(2);
        System.out.println("Get project by ID(2): ");
        System.out.println(restoredProject);

        System.out.println("\n========================================================================================\n");


        Set<Project> restoredProjects = javaIOProjectDAO.getAll();
        System.out.println("Get all teams: ");
        for (Project project : restoredProjects) System.out.println(project);


        Project testProject = new Project(3, "Test project", cheapTeams);
        Project fakeProject = new Project(4, "Fake project", expensiveTeams);
        Project notRealProject = new Project(5, "Not Real project", cheapTeams);
        Project illusionOfProject = new Project(6, "Illusion of Project project", expensiveTeams);


        javaIOProjectDAO.save(testProject);
        javaIOProjectDAO.save(fakeProject);
        javaIOProjectDAO.save(notRealProject);
        javaIOProjectDAO.save(illusionOfProject);
    }
}

