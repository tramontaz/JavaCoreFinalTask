package dao;

import model.Developer;
import model.Skill;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class JavaIOSkillDAOImpl implements SkillDAO {

    private String filePath = "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/skills.txt";
    private char split;

    public JavaIOSkillDAOImpl(String filePath) {
        this.filePath = filePath;
        this.split = ',';
    }

    @Override
    public void save(Skill skill) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringBuilder skillToString = new StringBuilder(skill.getId() + String.valueOf(split) + skill.getName() + "\n");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(skill.getId());
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
                bufferedWriter.write(skillToString.toString());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Skill skill) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(skill.getId()))) {
                    delete(skill.getId());
                }
                save(skill);
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        File skills = new File(filePath);
        File newSkills = new File("/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/temp.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(skills)));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newSkills, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(String.valueOf(id))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            skills.delete();
            newSkills.renameTo(skills);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill getById(long id) {
        Skill skillThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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

    @Override
    public Set<Skill> getAll() {
        Set<Skill> skills = new HashSet<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            String[] skillInStringArray;
            while ((line = bufferedReader.readLine()) != null) {
                skillInStringArray = line.split(String.valueOf(split));
                skills.add(new Skill(Long.parseLong(skillInStringArray[0]), skillInStringArray[1]));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skills;
    }
}
