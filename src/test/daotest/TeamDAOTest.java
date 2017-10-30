package test.daotest;

import dao.JavaIOTeamDAOImpl;
import model.Developer;
import model.Skill;
import model.Team;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TeamDAOTest {
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


        JavaIOTeamDAOImpl javaIOTeamDAO = new JavaIOTeamDAOImpl("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/teams.txt");

        System.out.println(firstTeam);
        System.out.println();
        System.out.println(secondTeam);

        System.out.println("\n========================================================================================\n");

        javaIOTeamDAO.save(firstTeam);
        javaIOTeamDAO.save(secondTeam);

        System.out.println("\n========================================================================================\n");

        firstTeam.setName("Team from Russia");
        rusTeam.remove(developer);
        firstTeam.setSet(rusTeam);
        System.out.println("The first team had changed: ");
        System.out.println(firstTeam);
        javaIOTeamDAO.update(firstTeam);

        System.out.println("\n========================================================================================\n");

//        javaIOTeamDAO.delete(1);

        System.out.println("\n========================================================================================\n");

        Team restoredTeam = javaIOTeamDAO.getById(2L);
        System.out.println("Get team by ID: ");
        System.out.println(restoredTeam);

        System.out.println("\n========================================================================================\n");


        Set<Team> restoredTeams = javaIOTeamDAO.getAll();
        System.out.println("Get all teams: ");
        for (Team team : restoredTeams) System.out.println(team);


        Team thirdTeam = new Team(3, "Third Team", rusTeam);
        Team fourthTeam = new Team(4, "Fourth Team", usTeam);
        Team fifthTeam = new Team(5, "Fifth Team", rusTeam);
        Team sixthTeam = new Team(6, "Sixth Team", usTeam);

        javaIOTeamDAO.save(thirdTeam);
        javaIOTeamDAO.save(fourthTeam);
        javaIOTeamDAO.save(fifthTeam);
        javaIOTeamDAO.save(sixthTeam);




    }
}
