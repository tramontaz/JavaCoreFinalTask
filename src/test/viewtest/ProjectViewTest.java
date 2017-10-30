package test.viewtest;

import view.ProjectView;
import view.ProjectViewImpl;

public class ProjectViewTest {
    public static void main(String[] args) {
        ProjectView projectView = new ProjectViewImpl();
        projectView.saveProject();
    }
}
