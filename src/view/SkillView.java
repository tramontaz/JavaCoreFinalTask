package view;

import dao.JavaIOSkillDAOImpl;
import model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkillView {
    public void saveSkill() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long id = 0;
        String name = null;
        Skill skill = null;
        boolean trigger = true;

        while (trigger) {
            System.out.print("Enter Id: ");
            try {
                id = Long.parseLong(in.readLine());
            } catch (NumberFormatException e) {
                System.err.println("Error! ID can be only a number!!!!");
                break;
            } catch (IOException e) {
                System.out.println("ID can be only a number");
                e.printStackTrace();
            }
            System.out.print("Enter the name: ");
            try {
                name = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (id != 0 && name != null) {
                skill = new Skill(id, name);
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
}
