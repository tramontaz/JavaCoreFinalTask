package view;

import dao.JavaIOTeamDAOImpl;
import model.Developer;
import model.Skill;
import model.Team;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TeamViewImpl implements TeamView {
    @Override
    public void saveTeam() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long id = 0;
        String name = null;
        Set<Developer> developersSet;
        Team team;
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

            //Set teams
            developersSet = new HashSet<>();
            String developersIDsToString;
            boolean successfulEntering;
            boolean isSomethingWrong = true;
            System.out.print("Please enter developer's ids split by ',' and press 'Enter' OR \"0\" to exit: ");
            while (isSomethingWrong) {
                successfulEntering = true;
                try {
                    developersIDsToString = in.readLine();

                    String[] developersInStringArray = developersIDsToString.split(",");
                    for (String integer : developersInStringArray) {
                        if (!(integer.matches("([+])?\\d+"))) {
                            System.err.println("You made a mistake when entered the IDs, try again.");
                            successfulEntering = false;
                        } else if (successfulEntering && Long.parseLong(integer) == 0) {
                            System.exit(0);
                        }
                    }
                    if (successfulEntering) {
                        Long[] ids = new Long[developersInStringArray.length];
                        for (int i = 0; i < ids.length; i++) {
                            ids[i] = Long.parseLong(developersInStringArray[i]);
                        }

                        try {
                        for (Long identification : ids) {
                            developersSet.add(getDeveloperById(identification));
                        }
                        isSomethingWrong = false;
                        } catch (NullPointerException e) {
                            System.err.println("There is no such a project!!! Please try again");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (name != null && !name.isEmpty()) {
                team = new Team(id, name, developersSet);
            } else {
                System.err.println("Please check your input...");
                continue;
            }


            JavaIOTeamDAOImpl javaIOTeamDAO = new JavaIOTeamDAOImpl(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/teams.txt");

            javaIOTeamDAO.save(team);
            System.out.println("Team: " + javaIOTeamDAO.getById(id).toString());
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
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/teams.txt")));
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

    private Set<Skill> restoredSkills(String line) {
        String subString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        String[] developersInStringArray = subString.split("#");
        Long[] ids = new Long[developersInStringArray.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(developersInStringArray[i]);
        }

        Set<Skill> restoredSkills = new HashSet<>();

        for (Long id : ids) {
            restoredSkills.add(getSkillById(id));
        }
        return restoredSkills;
    }

    private Developer getDeveloperById(long id) {
        Developer developerThatWillBeReturned = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "/home/dragon/IdeaProjects/JavaCoreFinalTask/src/resources/developers.txt")));
            String line;
            String[] developerInStringArray = null;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(','));
                if (substring.equals(String.valueOf(id))) {
                    developerInStringArray = line.split(",");
                    break;
                }
            }
            bufferedReader.close();
            if (developerInStringArray != null) {
                developerThatWillBeReturned = new Developer(Long.parseLong(developerInStringArray[0]),
                        developerInStringArray[1], developerInStringArray[2], developerInStringArray[3],
                        restoredSkills(developerInStringArray[4]), new BigDecimal(developerInStringArray[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (developerThatWillBeReturned != null) return developerThatWillBeReturned;
        else throw new NullPointerException();
    }

}
