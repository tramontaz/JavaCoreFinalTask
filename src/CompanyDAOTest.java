import dao.JavaIOCompanyDAOImpl;
import model.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CompanyDAOTest {
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
        Project testProject = new Project(3, "Test project", cheapTeams);
        Project fakeProject = new Project(4, "Fake project", expensiveTeams);
        Project notRealProject = new Project(5, "Not Real project", cheapTeams);
        Project illusionOfProject = new Project(6, "Illusion of Project project", expensiveTeams);


        Set<Project> successfulProjects = new HashSet<>();
        successfulProjects.add(siteProject);
        successfulProjects.add(bankingProject);

        Set<Project> losingProjects = new HashSet<>();
        losingProjects.add(testProject);
        losingProjects.add(fakeProject);
        losingProjects.add(notRealProject);
        losingProjects.add(illusionOfProject);


        Company google = new Company(1, "Google Inc.", successfulProjects);
        Company shmoogle = new Company(2, "OOO Smoogle", losingProjects);


        JavaIOCompanyDAOImpl javaIOCompanyDAO = new JavaIOCompanyDAOImpl("allCompany.txt");

        System.out.println(google);
        System.out.println();
        System.out.println(shmoogle);

        System.out.println("\n========================================================================================\n");

        javaIOCompanyDAO.save(google);
        javaIOCompanyDAO.save(shmoogle);

        System.out.println("\n========================================================================================\n");

        shmoogle.setName("Mail.ru");
        losingProjects.remove(fakeProject);
        shmoogle.setSet(losingProjects);
        System.out.println("Shmoogle had changed: ");
        System.out.println(shmoogle);
        javaIOCompanyDAO.update(shmoogle);

        System.out.println("\n========================================================================================\n");

        javaIOCompanyDAO.delete(2);

        System.out.println("\n========================================================================================\n");

        Company restoredCompany = javaIOCompanyDAO.getById(1);
        System.out.println("Get company by ID(1): ");
        System.out.println(restoredCompany);

        System.out.println("\n========================================================================================\n");


        Set<Company> restoredAllCompany = javaIOCompanyDAO.getAll();
        System.out.println("Get all company: ");
        for (Company company : restoredAllCompany) System.out.println(company);
    }
}
