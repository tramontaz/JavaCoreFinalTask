package test.viewtest;

import view.DeveloperView;
import view.DeveloperViewImpl;

public class DeveloperViewTest {
    public static void main(String[] args) {


        DeveloperView developerView = new DeveloperViewImpl();

        developerView.saveDeveloper();
    }
}
