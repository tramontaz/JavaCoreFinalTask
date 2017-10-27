package dao;

import model.Developer;
import model.Skill;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class JavaIODeveloperDAOImpl implements DeveloperDAO {
    private String filePath = "developers.txt";
    private char split;

    public JavaIODeveloperDAOImpl(String filePath) {
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

    @Override
    public void save(Developer developer) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder setSkillsToString = new StringBuilder("{");   //Displays a set of skills as a string
        for (Skill skill : developer.getSet()) {
            setSkillsToString.append(skill.getId() + "#");
        }
        setSkillsToString.deleteCharAt(setSkillsToString.length() - 1);
        setSkillsToString.append("}");

        StringBuilder developerToString = new StringBuilder(developer.getId() + String.valueOf(split) +
                developer.getFirstName() + String.valueOf(split) + developer.getLastName() + String.valueOf(split) +
                developer.getSpecialty() + String.valueOf(split) + setSkillsToString + String.valueOf(split) +
                developer.getSalary() + "\n");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(developer.getId());
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
                bufferedWriter.write(developerToString.toString());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Developer developer) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(developer.getId()))) {
                    delete(developer.getId());
                }
                save(developer);
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        File developers = new File(filePath);
        File newDevelopers = new File("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/temp.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(developers)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newDevelopers, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(String.valueOf(id))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            developers.delete();
            newDevelopers.renameTo(developers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(long id) {
        Developer developerThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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

    @Override
    public Set<Developer> getAll() {
        Set<Developer> developers = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] developersInArray;
            while ((line = bufferedReader.readLine()) != null) {
                developersInArray = line.split(String.valueOf(split));
                developers.add(new Developer(Long.parseLong(developersInArray[0]),
                        developersInArray[1], developersInArray[2], developersInArray[3],
                        restoredSkills(developersInArray[4]), new BigDecimal(developersInArray[5])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
    }
}
