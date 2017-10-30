package view;

import dao.JavaIOSkillDAOImpl;
import model.Skill;

import java.io.*;

public class SkillViewImpl implements SkillView {

    @Override
    public void saveSkill() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long id = 0;
        String name = null;
        Skill skill;
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

            //name
            System.out.print("Enter the name (must not be empty) or \"0\" to exit: ");
            try {
                name = in.readLine();
                if (name.equals("0")) System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!(name != null && name.isEmpty())) {
                skill = new Skill(id, name);
            } else {
                System.err.println("Please check your input...");
                continue;

            }

            JavaIOSkillDAOImpl javaIOSkillDAO = new JavaIOSkillDAOImpl(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/skills.txt");

            javaIOSkillDAO.save(skill);
            System.out.println("Skill: " + javaIOSkillDAO.getById(id).toString());
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
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/skills.txt")));
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
}
