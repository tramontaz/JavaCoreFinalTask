import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Long.*;

public class Main {
    public static void main(String[] args) {
        Developer firstDeveloper = new Developer("Vadim", "Chemodurov",
                "Junior Java Developer", 800);
        Developer secondDeveloper = new Developer("Leonid", "Malashenko",
                "Junior sql Developer", 790);
        Developer thirdDeveloper = new Developer("Valery", "Okhotnikov",
                "Junior OpenGL Developer", 810);



        Skill javaCore = new Skill("Java core");
        Skill git = new Skill("Git");
        Skill sql = new Skill("SQL");
        Skill cSharp = new Skill("C#");
        Standard skills = new Standard(new HashSet<>());
        skills.addSkillIntoSkillset(javaCore);
        skills.addSkillIntoSkillset(git);
        skills.addSkillIntoSkillset(sql);

        firstDeveloper.addSkill((Skill) skills.getSkillFromSkillSet(git.getId()));
        firstDeveloper.addSkill((Skill) skills.getSkillFromSkillSet(javaCore.getId()));
        firstDeveloper.addSkill((Skill) skills.getSkillFromSkillSet(git.getId()));


        Standard developers = new Standard(new HashSet<>());
        Standard teams = new Standard(new HashSet<>());
        Standard projects = new Standard(new HashSet<>());





//        create(secondDeveloper);
        create(firstDeveloper);
//        create(thirdDeveloper);
        create(cSharp);
        create(javaCore);
        create(git);
        create(sql);

        firstDeveloper.addSkill(javaCore);
        firstDeveloper.addSkill(git);
        firstDeveloper.addSkill(sql);


//        create(eshoppingTeam);
//        create(bankingProject);
//        create(usaCompany);
//        create(usaCustomer);

        HaveID skillThatWasRead = read("skills.txt", 4L);
        HaveID skillThatWasRead2 = read("developers.txt", 1L);
        System.out.println(firstDeveloper.getInfo());




    }

    private static Set<Skill> restoreSkills(HashSet<HaveID> standards){
        char open = '{';
        char close = '}';
        String substring = HaveID.getInfo().substring((int) open, (int) close);
        String[] stringSkills = substring.split("#");
        Set<Skill> restoredSkills = new HashSet<>();
        int x = 0;
        for (HaveID standart : standards) {
            if (standart.equals(Long.parseLong(stringSkills[x]))) {
                restoredSkills.add((Skill) standart);
            }
        } return restoredSkills;
    }

    private static void write(StringBuilder toFile, String filePath) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            bufferedWriter.write(toFile.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HaveID read(String filePath, Long id, Standard standard) {
        HaveID haveID = null;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = id.toString();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(idString)) {
                    stringBuilder.append(line);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] entityThatWasGot = stringBuilder.toString().split(",");

        if (filePath.equals("skills.txt")) {
            haveID = new Skill(parseLong(entityThatWasGot[0]), entityThatWasGot[1]);
        } else if (filePath.equals("companies.txt")){
            haveID = new Company(parseLong(entityThatWasGot[0]), entityThatWasGot[1],
                    new HashSet<Project>());
        } else if (filePath.equals("customers.txt")){
            haveID = new Customer(parseLong(entityThatWasGot[0]), entityThatWasGot[1], entityThatWasGot[2],
                    entityThatWasGot[1], new HashSet<>());
        } else if (filePath.equals("developers.txt")){
            haveID = new Developer(parseLong(entityThatWasGot[0]), entityThatWasGot[1], entityThatWasGot[2], entityThatWasGot[3],
                    , new BigDecimal(entityThatWasGot[5]));
        } else if (filePath.equals("projects.txt")){
            haveID = new Project(parseLong(entityThatWasGot[0]), entityThatWasGot[1], new HashSet<>());
        } else if (filePath.equals("teams.txt")){
            haveID = new Team(parseLong(entityThatWasGot[0]),entityThatWasGot[1], new HashSet<>());
        }
        else return null;
        return haveID;
    }

    private static boolean create(HaveID entity){
        String filePath;
        if (entity instanceof Company){
            filePath = "companies.txt";
        } else if (entity instanceof Customer){
            filePath = "customers.txt";
        } else if (entity instanceof Developer){
            filePath = "developers.txt";
        } else if (entity instanceof Project){
            filePath = "projects.txt";
        } else if (entity instanceof Skill){
            filePath = "skills.txt";
        } else if (entity instanceof Team){
            filePath = "teams.txt";
        } else {
            filePath = "unknownEntity.txt";
        }

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            String idString = String.valueOf(entity.getId());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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
            write(entity.getInfo(), filePath);
            return true;
        } else {
            return false;
        }
    }
}
