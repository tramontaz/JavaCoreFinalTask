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
                "Junior C++ Developer", 790);
        Developer thirdDeveloper = new Developer("Valery", "Okhotnikov",
                "Junior OpenGL Developer", 810);
        Set<Skill> firstSkillSet = new HashSet<>();
        firstSkillSet.add(new Skill("Java core"));
        firstSkillSet.add(new Skill("Git"));
        firstSkillSet.add(new Skill("SQL"));
        firstDeveloper.setSkills(firstSkillSet);

        StringBuilder skills = skillsForOutput(firstSkillSet);

        StringBuilder developerToFile = new StringBuilder(firstDeveloper.getId() + "," + firstDeveloper.getFirstName() + ","
                + firstDeveloper.getLastName() + "," + firstDeveloper.getSpecialty() + "," +
                skills + "," + firstDeveloper.getSalary() + "\n");

        StringBuilder skillsToFile = new StringBuilder();
        for (Skill skill : firstSkillSet) {
            skillsToFile.append(skill.getId() + ". " + skill.getName() + "\n");
        }

        write(developerToFile, "developers.txt");
        write(skillsToFile, "skills.txt");

    }

    private static StringBuilder skillsForOutput(Set<Skill> firstSkillSet) {
        StringBuilder skills = new StringBuilder("{");
        for (Skill skill : firstSkillSet){
            skills.append(skill.getId() + ",");
        }
        skills.deleteCharAt(skills.length() - 1);
        skills.append("}");
        return skills;
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
