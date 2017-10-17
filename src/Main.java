import java.io.*;
import java.math.BigDecimal;
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
        skills.addSkillIntoEntitySet(javaCore);
        skills.addSkillIntoEntitySet(git);
        skills.addSkillIntoEntitySet(sql);

        firstDeveloper.addSkill((Skill) skills.getSkillFromEntitySet(git.getId()));
        firstDeveloper.addSkill((Skill) skills.getSkillFromEntitySet(javaCore.getId()));
        firstDeveloper.addSkill((Skill) skills.getSkillFromEntitySet(git.getId()));


        Standard developers = new Standard(new HashSet<>());
        Standard teams = new Standard(new HashSet<>());
        Standard projects = new Standard(new HashSet<>());


        create(secondDeveloper);
        create(firstDeveloper);
        create(thirdDeveloper);


//        write(firstDeveloper.getInfo(), "developers.txt");


//        create(eshoppingTeam);
//        create(bankingProject);
//        create(usaCompany);
//        create(usaCustomer);

        System.out.println((read("developers.txt", 1L, skills)).getInfo());


    }

    private static Set<HaveID> restoreSet(String entityToString, Standard standard) {
        Set<HaveID> restoredEntity = new HashSet<>();
        String[] parsedString = entityToString.split("#");
        Long[] ids = new Long[parsedString.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(parsedString[i]);
        }

        Set<HaveID> entitySet = standard.getEntitySet();
        for (HaveID anEntitySet : entitySet) {
            long g = anEntitySet.getId();
            for (Long id : ids) {
                if (g == id) {
                    restoredEntity.add(standard.getSkillFromEntitySet(g));
                }
            }
        }
        return restoredEntity;
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
        HaveID haveID;

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
        String subString = stringBuilder.substring(stringBuilder.indexOf("{") + 1, stringBuilder.indexOf("}"));

        switch (filePath) {
            case "skills.txt":
                haveID = new Skill(parseLong(entityThatWasGot[0]), entityThatWasGot[1]);
                break;
            case "companies.txt":
                haveID = new Company(parseLong(entityThatWasGot[0]), entityThatWasGot[1],
                        restoreSet(subString, standard));
                break;
            case "customers.txt":
                haveID = new Customer(parseLong(entityThatWasGot[0]), entityThatWasGot[1], entityThatWasGot[2],
                        entityThatWasGot[1], restoreSet(subString, standard));
                break;
            case "developers.txt":
                haveID = new Developer(parseLong(entityThatWasGot[0]), entityThatWasGot[1], entityThatWasGot[2],
                        entityThatWasGot[3], restoreSet(subString, standard), new BigDecimal(entityThatWasGot[5]));
                break;
            case "projects.txt":
                haveID = new Project(parseLong(entityThatWasGot[0]), entityThatWasGot[1], restoreSet(subString, standard));
                break;
            case "teams.txt":
                haveID = new Team(parseLong(entityThatWasGot[0]), entityThatWasGot[1], restoreSet(subString, standard));
                break;
            default:
                return null;
        }
        return haveID;
    }

    private static boolean create(HaveID entity) {
        String filePath;
        if (entity instanceof Company) {
            filePath = "companies.txt";
        } else if (entity instanceof Customer) {
            filePath = "customers.txt";
        } else if (entity instanceof Developer) {
            filePath = "developers.txt";
        } else if (entity instanceof Project) {
            filePath = "projects.txt";
        } else if (entity instanceof Skill) {
            filePath = "skills.txt";
        } else if (entity instanceof Team) {
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
