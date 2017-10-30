package dao;

import model.Developer;
import model.Skill;
import model.Team;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class JavaIOTeamDAOImpl implements TeamDAO {
    private String filePath;
    private char split;

    public JavaIOTeamDAOImpl(String filePath) {
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



    @Override
    public void save(Team team) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder setDevelopersToString = new StringBuilder("{");   //Displays a set of skills as a string
        for (Developer developer : team.getSet()) {
            setDevelopersToString.append(developer.getId()).append("#");
        }
        setDevelopersToString.deleteCharAt(setDevelopersToString.length() - 1);
        setDevelopersToString.append("}");

        StringBuilder teamToString = new StringBuilder(team.getId() + String.valueOf(split) +
                team.getName() + String.valueOf(split) + setDevelopersToString + "\n");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(team.getId());
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
                bufferedWriter.write(teamToString.toString());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Team team) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(team.getId()))) {
                    delete(team.getId());
                }
                save(team);
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        File teams = new File(filePath);
        File newTeams = new File("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/temp.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(teams)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newTeams, true));
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
            teams.delete();
            newTeams.renameTo(teams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team getById(long id) {
        Team teamThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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

    @Override
    public Set<Team> getAll() {
        Set<Team> teams = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] teamInStringArray;
            while ((line = bufferedReader.readLine()) != null) {
                teamInStringArray = line.split(String.valueOf(split));
                teams.add(new Team(Long.parseLong(teamInStringArray[0]),
                        teamInStringArray[1], restoredDevelopers(teamInStringArray[2])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }
}
