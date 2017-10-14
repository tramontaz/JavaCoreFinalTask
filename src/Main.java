import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Developer firstDeveloper = new Developer("Vadim", "Chemodurov",
                "Junior Java Developer", 800);
        Developer secondDeveloper = new Developer("Leonid", "Malashenko",
                "Junior sql Developer", 790);
        Developer thirdDeveloper = new Developer("Valery", "Okhotnikov",
                "Junior OpenGL Developer", 810);


        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(new Skill("Java core"));
        skillSet.add(new Skill("Git"));
        skillSet.add(new Skill("SQL"));
        firstDeveloper.setSkills(skillSet);

        StringBuilder skills = skillsForOutput(skillSet);

        StringBuilder developerToFile = new StringBuilder(firstDeveloper.getId() + "," + firstDeveloper.getFirstName() + ","
                + firstDeveloper.getLastName() + "," + firstDeveloper.getSpecialty() + "," +
                skills + "," + firstDeveloper.getSalary() + "\n");

        StringBuilder skillsToFile = new StringBuilder();
        for (Skill skill : skillSet) {
            skillsToFile.append(skill.getId() + ". " + skill.getName() + "\n");
        }

        Team bankingTeam = new Team("Banking Team");
        Team visitPageTeam = new Team("Visit Page Team");
        Team eshoppingTeam = new Team("E-shopping team");
        bankingTeam.addDeveloperIntoTeam(firstDeveloper);
        bankingTeam.addDeveloperIntoTeam(secondDeveloper);
        bankingTeam.addDeveloperIntoTeam(thirdDeveloper);
        Set<Developer> developers = new HashSet<>();
        developers.add(firstDeveloper);
        developers.add(secondDeveloper);
        developers.add(thirdDeveloper);

        StringBuilder developersForOutput = skillsForOutput(developers);
        StringBuilder teamToFile = new StringBuilder(bankingTeam.getId() + "," + bankingTeam.getName() + "," +
                developersForOutput);

        Project bankingProject = new Project("Banking Project");
        Project visitSiteProject = new Project("Visit site project");
        Project eshoppingProject = new Project("Ethernet shopping project");
        bankingProject.addTeamIntoProject(bankingTeam);
        Set<Team> teams = new HashSet<>();
        teams.add(bankingTeam);
        teams.add(visitPageTeam);
        teams.add(eshoppingTeam);


        StringBuilder teamsForOutput = skillsForOutput(teams);
        StringBuilder projectToFile = new StringBuilder(bankingProject.getId() + ","
                + bankingProject.getName() + "," + teamsForOutput);

        Company usaCompany = new Company("Company from USA Inc.");
        Company canadianCompany = new Company("Company from Canada Inc.");
        Company australianCompany = new Company("Company from Australia Inc.");
        usaCompany.addProjectForCompany(bankingProject);
        Set<Project> projects = new HashSet<>();
        projects.add(bankingProject);
        projects.add(visitSiteProject);
        projects.add(eshoppingProject);




        write(developerToFile, "developers.txt");
        write(skillsToFile, "skills.txt");
        write(teamToFile, "teams.txt");
        write(projectToFile, "projects.txt");

    }

    private static <T extends HaveID > StringBuilder skillsForOutput(Set<T> entityes) {
        StringBuilder entitiesToString = new StringBuilder("{");
        for (T entity : entityes){
            entitiesToString.append(entity.getId() + ",");
        }
        entitiesToString.deleteCharAt(entitiesToString.length() - 1);
        entitiesToString.append("}");
        return entitiesToString;
    }

    static void write(StringBuilder toFile, String filePath) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(toFile.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
