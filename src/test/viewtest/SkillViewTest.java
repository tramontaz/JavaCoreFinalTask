package test.viewtest;


import view.SkillView;
import view.SkillViewImpl;

public class SkillViewTest {
    public static void main(String[] args) {
        SkillView skillView = new SkillViewImpl();
        skillView.saveSkill();
    }
}
