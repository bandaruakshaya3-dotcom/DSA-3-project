package main;

import java.io.*;
import java.util.ArrayList;

import model.Player;
import model.Team;
import model.Match;

public class DataManager {

    static String folder = "data/";

    public static ArrayList<Player> loadPlayers() {

        ArrayList<Player> players = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                        new FileReader(folder + "players.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\\|", 7);

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String team = parts[2];
                int runs = Integer.parseInt(parts[3]);
                int matches = Integer.parseInt(parts[4]);
                double average = Double.parseDouble(parts[5]);
                String performance = parts[6];

                players.add(
                    new Player(
                        id,
                        name,
                        team,
                        runs,
                        matches,
                        average,
                        performance
                    )
                );
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    public static ArrayList<Team> loadTeams() {

        ArrayList<Team> teams = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                        new FileReader(folder + "teams.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];

                teams.add(new Team(id, name));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return teams;
    }

    public static ArrayList<Match> loadMatches() {

        ArrayList<Match> matches = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                        new FileReader(folder + "matches.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\\|", 4);

                int id = Integer.parseInt(parts[0]);
                String team1 = parts[1];
                String team2 = parts[2];
                String commentary = parts[3];

                matches.add(
                    new Match(
                        id,
                        team1,
                        team2,
                        commentary
                    )
                );
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matches;
    }

    public static void savePlayer(Player p) {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                        new FileWriter(
                            folder + "players.txt",
                            true
                        )
                    );

            bw.write(
                p.getId() + "|" +
                p.getName() + "|" +
                p.getTeam() + "|" +
                p.getRuns() + "|" +
                p.getMatches() + "|" +
                p.getAverage() + "|" +
                p.getPerformance()
            );

            bw.newLine();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveTeam(Team t) {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                        new FileWriter(
                            folder + "teams.txt",
                            true
                        )
                    );

            bw.write(
                t.getId() + "|" +
                t.getName()
            );

            bw.newLine();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveMatch(Match m) {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                        new FileWriter(
                            folder + "matches.txt",
                            true
                        )
                    );

            bw.write(
                m.getId() + "|" +
                m.getTeam1() + "|" +
                m.getTeam2() + "|" +
                m.getCommentary()
            );

            bw.newLine();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}