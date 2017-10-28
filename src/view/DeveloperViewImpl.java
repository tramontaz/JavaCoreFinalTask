package view;

import dao.JavaIODeveloperDAOImpl;
import model.Developer;
import model.Skill;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeveloperViewImpl implements DeveloperView {

    @Override
    public void saveDeveloper() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long id = 0;
        String firstName = null;
        String lastName = null;
        String specialty = null;
        Set<Skill> skillSet;
        BigDecimal salary = null;
        Developer developer;
        boolean trigger = true;

        while (trigger) {
            //ID
            System.out.print("Enter Id or \"0\" to exit: ");
            try {
                id = Long.parseLong(in.readLine());
                if (!ifThereIsNoSuchID(id)) {
                    System.err.println("\nThis ID already exists!");
                    continue;
                }
                if (id == 0) System.exit(0);
                if (id < 0) {
                    System.err.println("\nID should be only natural number (more than '0')");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error! ID can be only a number!!!!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            //First name
            System.out.print("Enter the first name or \"0\" to exit: ");
            try {
                firstName = in.readLine();
                if (firstName.equals("0")) System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Last name
            System.out.print("Enter the last name or \"0\" to exit: ");
            try {
                lastName = in.readLine();
                if (lastName.equals("0")) System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Specialty
            System.out.print("Enter the specialty or \"0\" to exit: ");
            try {
                specialty = in.readLine();
                if (specialty.equals("0")) System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Set skills
            skillSet = new HashSet<>();
            String skillsIDsToString;
            boolean successfulEntering = true;
            boolean isSomethingWrong = true;
            System.out.print("Please enter developer's skill's ids split by ',' and press 'Enter' OR \"0\" to exit: ");
            while (isSomethingWrong) {
                successfulEntering = true;
                try {
                    skillsIDsToString = in.readLine();

                    String[] skillsInStringArray = skillsIDsToString.split(",");
                    for (String integers : skillsInStringArray) {
                        if (!(integers.matches("([-+])?\\d+"))) {
                            System.err.println("You made a mistake when entered the IDs, try again.");
                            successfulEntering = false;
                        }
                    }
                    if (successfulEntering) {
                        Long[] ids = new Long[skillsInStringArray.length];
                        for (int i = 0; i < ids.length; i++) {
                            ids[i] = Long.parseLong(skillsInStringArray[i]);
                        }

                        for (Long identification : ids) {
                            skillSet.add(getSkillById(identification));
                        }
                        isSomethingWrong = false;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Salary
            isSomethingWrong = true;
            while (isSomethingWrong) {
                System.out.print("Enter Salary or \"0\" to exit: ");
                try {
                    String line = in.readLine();
                    if (!(line.matches("([-+])?\\d+"))) {
                        System.err.println("You made a mistake when entered the salary, try again.");
                        continue;
                    }
                    BigDecimal temp = new BigDecimal(line);
                    BigDecimal abortOperation = new BigDecimal(0);
                    switch (temp.compareTo(abortOperation)) {
                        case 0:
                            isSomethingWrong = false;
                            System.exit(0);
                            break;
                        case -1:
                            System.err.println("\nID should be only natural number (more than '0')");
                            continue;
                        case 1:
                            salary = temp;
                            isSomethingWrong = false;
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error! ID can be only a number!!!!");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (!(firstName != null && firstName.isEmpty() &&
                    lastName != null && lastName.isEmpty() &&
                    specialty != null && specialty.isEmpty() &&
                    !successfulEntering && isSomethingWrong
            )) {
                developer = new Developer(id, firstName, lastName, specialty, skillSet, salary);
            } else {
                System.err.println("Please check your input...");
                continue;

            }

            JavaIODeveloperDAOImpl javaIODeveloperDAO = new JavaIODeveloperDAOImpl(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/developers.txt");

            javaIODeveloperDAO.save(developer);
            System.out.println("Skill: " + javaIODeveloperDAO.getById(id).toString());
            System.out.println("Enter anything you want if you want to continue OR enter \"done!!!\" if you finished.");
            try {
                if (in.readLine().equals("done!!!")) trigger = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean ifThereIsNoSuchID(long id) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/developers.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    return false;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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
                    skillInStringArray = line.split(",");
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
}
