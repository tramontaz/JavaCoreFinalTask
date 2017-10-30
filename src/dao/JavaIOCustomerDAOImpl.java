package dao;

import model.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class JavaIOCustomerDAOImpl implements CustomerDAO {
    private String filePath;
    private char split;

    public JavaIOCustomerDAOImpl(String filePath) {
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
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
            if (teamInStringArray != null) {
                teamThatWillBeReturned = new Team(Long.parseLong(teamInStringArray[0]),
                        teamInStringArray[1], restoredDevelopers(teamInStringArray[2]));
            }
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
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
    public void save(Customer customer) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder setProjectsToString = new StringBuilder("{");   //Displays a set of skills as a string
        for (Project project : customer.getSet()) {
            setProjectsToString.append(project.getId()).append("#");
        }
        setProjectsToString.deleteCharAt(setProjectsToString.length() - 1);
        setProjectsToString.append("}");

        StringBuilder companyToString = new StringBuilder(customer.getId() + String.valueOf(split) +
                customer.getFirstName() + String.valueOf(split) + customer.getLastName() + String.valueOf(split) +
                customer.getAddress() + String.valueOf(split) + setProjectsToString + "\n");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(customer.getId());
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
    public void update(Customer customer) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(customer.getId()))) {
                    delete(customer.getId());
                }
                save(customer);
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        File customers = new File(filePath);
        File newCustomers = new File("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/temp.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(customers)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newCustomers, true));
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
            customers.delete();
            newCustomers.renameTo(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getById(long id) {
        Customer customerThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] customersInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    customersInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            if (customersInStringArray != null) {
                customerThatWillBeReturned = new Customer(Long.parseLong(customersInStringArray[0]),
                        customersInStringArray[1], customersInStringArray[2], customersInStringArray[3],
                        restoreProjects(customersInStringArray[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customerThatWillBeReturned;
    }

    @Override
    public Set<Customer> getAll() {
        Set<Customer> restoredCustomers = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] customersInStringArray;
            while ((line = bufferedReader.readLine()) != null) {
                customersInStringArray = line.split(String.valueOf(split));
                restoredCustomers.add(new Customer(Long.parseLong(customersInStringArray[0]),
                        customersInStringArray[1], customersInStringArray[2], customersInStringArray[3],
                        restoreProjects(customersInStringArray[4])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restoredCustomers;
    }
}
