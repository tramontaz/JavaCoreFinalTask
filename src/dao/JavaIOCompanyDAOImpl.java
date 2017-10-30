package dao;

import model.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class JavaIOCompanyDAOImpl implements CompanyDAO {
    private String filePath;
    private char split;

    public JavaIOCompanyDAOImpl(String filePath) {
        this.filePath = filePath;
        this.split = ',';
    }

    private Skill getSkillById(long id) {
        Skill skillThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/skills.txt")));
            String line;
            String[] skillInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    skillInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            if (skillInStringArray != null) {
                skillThatWillBeReturned = new Skill(Long.parseLong(skillInStringArray[0]), skillInStringArray[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skillThatWillBeReturned;
    }

    private Set<Skill> restoredSkills(String line) {
        String subString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        String[] developersInStringArray = subString.split("#");
        Long[] ids = new Long[developersInStringArray.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(developersInStringArray[i]);
        }

        Set<Skill> restoredSkills = new HashSet<>();

        for (Long id : ids) {
            restoredSkills.add(getSkillById(id));
        }
        return restoredSkills;
    }

    private Developer getDeveloperById(long id) {
        Developer developerThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/developers.txt")));
            String line;
            String[] developerInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    developerInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            if (developerInStringArray != null) {
                developerThatWillBeReturned = new Developer(Long.parseLong(developerInStringArray[0]),
                        developerInStringArray[1], developerInStringArray[2], developerInStringArray[3],
                        restoredSkills(developerInStringArray[4]), new BigDecimal(developerInStringArray[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return developerThatWillBeReturned;
    }

    private Set<Developer> restoredDevelopers(String line) {
        String subString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        String[] developersInStringArray = subString.split("#");
        Long[] ids = new Long[developersInStringArray.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(developersInStringArray[i]);
        }

        Set<Developer> restoredDevelopers = new HashSet<>();

        for (Long id : ids) {
            restoredDevelopers.add(getDeveloperById(id));
        }
        return restoredDevelopers;
    }

    private Team getTeamById(long id){
        Team teamThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("" +
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/teams.txt")));
            String line;
            String[] teamInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    teamInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            assert teamInStringArray != null;
            teamThatWillBeReturned = new Team(Long.parseLong(teamInStringArray[0]),
                    teamInStringArray[1], restoredDevelopers(teamInStringArray[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teamThatWillBeReturned;
    }


    private Set<Team> restoredTeams(String line) {
        String subString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        String[] teamsInStringArray = subString.split("#");
        Long[] ids = new Long[teamsInStringArray.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(teamsInStringArray[i]);
        }

        Set<Team> restoredTeams = new HashSet<>();

        for (Long id : ids) {
            restoredTeams.add(getTeamById(id));
        }
        return restoredTeams;
    }

    private Project getProjectById(long id){
        Project projectThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("" +
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/projects.txt")));
            String line;
            String[] projectInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    projectInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            if (projectInStringArray != null) {
                projectThatWillBeReturned = new Project(Long.parseLong(projectInStringArray[0]),
                        projectInStringArray[1], restoredTeams(projectInStringArray[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectThatWillBeReturned;
    }

    private Set<Project> restoreProjects(String line){
        String subString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        String[] projectsInStringArray = subString.split("#");
        Long[] ids = new Long[projectsInStringArray.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(projectsInStringArray[i]);
        }

        Set<Project> restoredProjects = new HashSet<>();

        for (Long id : ids) {
            restoredProjects.add(getProjectById(id));
        }
        return restoredProjects;
    }


    @Override
    public void save(Company company) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder setProjectsToString = new StringBuilder("{");   //Displays a set of skills as a string
        for (Project project : company.getSet()) {
            setProjectsToString.append(project.getId()).append("#");
        }
        setProjectsToString.deleteCharAt(setProjectsToString.length() - 1);
        setProjectsToString.append("}");

        StringBuilder companyToString = new StringBuilder(company.getId() + String.valueOf(split) +
                company.getName() + String.valueOf(split) + setProjectsToString + "\n");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(company.getId());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(idString)) {
                    stringBuilder.append(line);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stringBuilder.toString().isEmpty()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                bufferedWriter.write(companyToString.toString());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Company company) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(company.getId()))) {
                    delete(company.getId());
                }
                save(company);
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        File allCompany = new File(filePath);
        File newAllCompany = new File("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/temp.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(allCompany)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newAllCompany, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (!substring.equals(String.valueOf(id))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            allCompany.delete();
            newAllCompany.renameTo(allCompany);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company getById(long id) {
        Company companyThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] allCompanyInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (!substring.equals(String.valueOf(id))) {
                    allCompanyInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            assert allCompanyInStringArray != null;
            companyThatWillBeReturned = new Company(Long.parseLong(allCompanyInStringArray[0]),
                    allCompanyInStringArray[1], restoreProjects(allCompanyInStringArray[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return companyThatWillBeReturned;
    }

    @Override
    public Set<Company> getAll() {
        Set<Company> allCompany = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] allCompanyInStringArray;
            while ((line = bufferedReader.readLine()) != null) {
                allCompanyInStringArray = line.split(String.valueOf(split));
                allCompany.add(new Company(Long.parseLong(allCompanyInStringArray[0]),
                        allCompanyInStringArray[1], restoreProjects(allCompanyInStringArray[2])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCompany;
    }
}
