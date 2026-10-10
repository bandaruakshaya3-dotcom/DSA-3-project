package main;

import java.util.ArrayList;
import java.util.Scanner;

import algorithms.EditDistance;
import algorithms.KMP;
import algorithms.Trie;

import model.Player;
import model.Team;
import model.Match;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Player> players =
                DataManager.loadPlayers();

        ArrayList<Team> teams =
                DataManager.loadTeams();

        ArrayList<Match> matches =
                DataManager.loadMatches();

        Trie playerTrie = new Trie();

        for (Player p : players) {
            playerTrie.insert(p.getName());
        }

        int playerId = players.size() + 1;
        int teamId = teams.size() + 1;
        int matchId = matches.size() + 1;

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("          SPORTSPULSE");
            System.out.println("================================");
            System.out.println("1. Add Player");
            System.out.println("2. Add Team");
            System.out.println("3. Add Match");
            System.out.println("4. Search Player");
            System.out.println("5. Search Match Commentary");
            System.out.println("6. Compare Player Performance");
            System.out.println("7. View Players");
            System.out.println("8. View Teams");
            System.out.println("9. View Matches");
            System.out.println("10. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.println();
                System.out.println("========== ADD PLAYER ==========");

                System.out.print("Enter player name: ");
                String name = sc.nextLine();

                System.out.print("Enter team name: ");
                String team = sc.nextLine();

                System.out.print("Enter total runs: ");
                int runs = sc.nextInt();

                System.out.print("Enter matches played: ");
                int playerMatches = sc.nextInt();

                System.out.print("Enter batting average: ");
                double average = sc.nextDouble();

                sc.nextLine();

                System.out.print("Enter performance summary: ");
                String performance = sc.nextLine();

                Player p = new Player(
                    playerId,
                    name,
                    team,
                    runs,
                    playerMatches,
                    average,
                    performance
                );

                players.add(p);
                playerTrie.insert(name);

                DataManager.savePlayer(p);

                playerId++;

                System.out.println();
                System.out.println("Player added and saved successfully.");

                break;

            case 2:

                System.out.println();
                System.out.println("========== ADD TEAM ==========");

                System.out.print("Enter team name: ");
                String teamName = sc.nextLine();

                Team t = new Team(
                    teamId,
                    teamName
                );

                teams.add(t);

                DataManager.saveTeam(t);

                teamId++;

                System.out.println();
                System.out.println("Team added and saved successfully.");

                break;

            case 3:

                System.out.println();
                System.out.println("========== ADD MATCH ==========");

                System.out.print("Enter first team: ");
                String team1 = sc.nextLine();

                System.out.print("Enter second team: ");
                String team2 = sc.nextLine();

                System.out.print("Enter match commentary: ");
                String commentary = sc.nextLine();

                Match m = new Match(
                    matchId,
                    team1,
                    team2,
                    commentary
                );

                matches.add(m);

                DataManager.saveMatch(m);

                matchId++;

                System.out.println();
                System.out.println("Match added and saved successfully.");

                break;

            case 4:

                System.out.println();
                System.out.println("========== PLAYER SEARCH ==========");

                System.out.print("Enter player name: ");
                String searchName = sc.nextLine();

                if (playerTrie.search(searchName)) {

                    for (Player p1 : players) {

                        if (p1.getName().equalsIgnoreCase(searchName)) {

                            System.out.println();
                            System.out.println("Player Found");
                            System.out.println("-----------------------------");
                            System.out.println("ID       : " + p1.getId());
                            System.out.println("Name     : " + p1.getName());
                            System.out.println("Team     : " + p1.getTeam());
                            System.out.println("Runs     : " + p1.getRuns());
                            System.out.println("Matches  : " + p1.getMatches());
                            System.out.println("Average  : " + p1.getAverage());
                            System.out.println("Performance: "
                                    + p1.getPerformance());

                            break;
                        }
                    }

                } else {

                    System.out.println("Player not found.");
                }

                break;

            case 5:

                System.out.println();
                System.out.println("========== COMMENTARY SEARCH ==========");

                System.out.print("Enter keyword: ");
                String pattern = sc.nextLine();

                boolean found = false;

                for (Match match : matches) {

                    int position =
                            KMP.search(
                                match.getCommentary(),
                                pattern
                            );

                    if (position != -1) {

                        System.out.println();
                        System.out.println("Keyword found!");
                        System.out.println("-----------------------------");
                        System.out.println(
                            "Match : " +
                            match.getTeam1() +
                            " vs " +
                            match.getTeam2()
                        );

                        System.out.println(
                            "Position : " + position
                        );

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Keyword not found.");
                }

                break;

            case 6:

                System.out.println();
                System.out.println("========== PLAYER PERFORMANCE ==========");

                if (players.size() < 2) {

                    System.out.println(
                        "At least two players are required."
                    );

                    break;
                }

                System.out.println();
                System.out.println("Available Players:");

                for (Player p2 : players) {

                    System.out.println(
                        p2.getId() +
                        ". " +
                        p2.getName()
                    );
                }

                System.out.print(
                    "Enter first player ID: "
                );

                int firstId = sc.nextInt();

                System.out.print(
                    "Enter second player ID: "
                );

                int secondId = sc.nextInt();

                sc.nextLine();

                Player firstPlayer = null;
                Player secondPlayer = null;

                for (Player p3 : players) {

                    if (p3.getId() == firstId) {
                        firstPlayer = p3;
                    }

                    if (p3.getId() == secondId) {
                        secondPlayer = p3;
                    }
                }

                if (firstPlayer == null ||
                    secondPlayer == null) {

                    System.out.println(
                        "Invalid player ID."
                    );

                    break;
                }

                System.out.println();
                System.out.println("========== COMPARISON ==========");

                System.out.println();
                System.out.println(
                    "Player 1: " +
                    firstPlayer.getName()
                );

                System.out.println(
                    "Runs: " +
                    firstPlayer.getRuns()
                );

                System.out.println(
                    "Average: " +
                    firstPlayer.getAverage()
                );

                System.out.println(
                    "Performance: " +
                    firstPlayer.getPerformance()
                );

                System.out.println();
                System.out.println(
                    "Player 2: " +
                    secondPlayer.getName()
                );

                System.out.println(
                    "Runs: " +
                    secondPlayer.getRuns()
                );

                System.out.println(
                    "Average: " +
                    secondPlayer.getAverage()
                );

                System.out.println(
                    "Performance: " +
                    secondPlayer.getPerformance()
                );

                int distance =
                        EditDistance.findDistance(
                            firstPlayer.getPerformance(),
                            secondPlayer.getPerformance()
                        );

                System.out.println();
                System.out.println(
                    "Edit Distance: " +
                    distance
                );

                System.out.println(
                    "Performance records compared successfully."
                );

                break;

            case 7:

                System.out.println();
                System.out.println("========== PLAYERS ==========");

                if (players.isEmpty()) {

                    System.out.println("No players available.");

                } else {

                    for (Player p4 : players) {
                        p4.display();
                    }
                }

                break;

            case 8:

                System.out.println();
                System.out.println("========== TEAMS ==========");

                if (teams.isEmpty()) {

                    System.out.println("No teams available.");

                } else {

                    for (Team t1 : teams) {
                        t1.display();
                    }
                }

                break;

            case 9:

                System.out.println();
                System.out.println("========== MATCHES ==========");

                if (matches.isEmpty()) {

                    System.out.println("No matches available.");

                } else {

                    for (Match m1 : matches) {
                        m1.display();
                    }
                }

                break;

            case 10:

                System.out.println();
                System.out.println(
                    "Thank you for using SportsPulse."
                );

                sc.close();
                return;

            default:

                System.out.println(
                    "Invalid choice."
                );
            }
        }
    }
}