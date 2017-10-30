package test.viewtest;

import view.TeamView;
import view.TeamViewImpl;

public class TeamViewTests {
    public static void main(String[] args) {
        TeamView teamView = new TeamViewImpl();
        teamView.saveTeam();
    }
}
