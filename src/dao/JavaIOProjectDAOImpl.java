package dao;

import model.Developer;
import model.Project;
import model.Skill;
import model.Team;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class JavaIOProjectDAOImpl implements ProjectDAO {
    private String filePath;
    private char split;

    public JavaIOProjectDAOImpl(String filePath) {
        this.filePath = filePath;
        this.split = ',';
    }

    private Skill getSkillById(long id) {
        Skill skillThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("skills.txt")));
            String line;
            String[] skillInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(id))) {
                    skillInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            skillThatWillBeReturned = new Skill(Long.parseLong(skillInStringArray[0]), skillInStringArray[1]);
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("developers.txt")));
            String line;
            String[] developerInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(id))) {
                    developerInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            developerThatWillBeReturned = new Developer(Long.parseLong(developerInStringArray[0]),
                    developerInStringArray[1], developerInStringArray[2], developerInStringArray[3],
                    restoredSkills(developerInStringArray[4]), new BigDecimal(developerInStringArray[5]));
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("teams.txt")));
            String line;
            String[] teamInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(id))) {
                    teamInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
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


    @Override
    public void save(Project project) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder setTeamsToString = new StringBuilder("{");   //Displays a set of skills as a string
        for (Team team : project.getSet()) {
            setTeamsToString.append(team.getId() + "#");
        }
        setTeamsToString.deleteCharAt(setTeamsToString.length() - 1);
        setTeamsToString.append("}");

        StringBuilder projectToString = new StringBuilder(project.getId() + String.valueOf(split) +
                project.getName() + String.valueOf(split) + setTeamsToString + "\n");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(project.getId());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(idString)) {
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
                bufferedWriter.write(projectToString.toString());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Project project) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(project.getId()))) {
                    delete(project.getId());
                }
                save(project);
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        File projects = new File(filePath);
        File newProjects = new File("temp.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(projects)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newProjects, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(String.valueOf(id))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            projects.delete();
            newProjects.renameTo(projects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project getById(long id) {
        Project projectThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] projectInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(id))) {
                    projectInStringArray = line.split(String.valueOf(split));
                    break;
                }
            }
            bufferedReader.close();
            projectThatWillBeReturned = new Project(Long.parseLong(projectInStringArray[0]),
                    projectInStringArray[1], restoredTeams(projectInStringArray[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectThatWillBeReturned;
    }

    @Override
    public Set<Project> getAll() {
        Set<Project> projects = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] projectsInStringArray;
            while ((line = bufferedReader.readLine()) != null) {
                projectsInStringArray = line.split(String.valueOf(split));
                projects.add(new Project(Long.parseLong(projectsInStringArray[0]),
                        projectsInStringArray[1], restoredTeams(projectsInStringArray[2])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
