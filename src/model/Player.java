package model;

public class Player {

    private int id;
    private String name;
    private String team;
    private int runs;
    private int matches;
    private double average;
    private String performance;

    public Player(int id, String name, String team,
                  int runs, int matches,
                  double average, String performance) {

        this.id = id;
        this.name = name;
        this.team = team;
        this.runs = runs;
        this.matches = matches;
        this.average = average;
        this.performance = performance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getRuns() {
        return runs;
    }

    public int getMatches() {
        return matches;
    }

    public double getAverage() {
        return average;
    }

    public String getPerformance() {
        return performance;
    }

    public void display() {

        System.out.println(
            id + " - " + name +
            " - " + team +
            " - Runs: " + runs +
            " - Matches: " + matches +
            " - Average: " + average
        );

        System.out.println(
            "   Performance: " + performance
        );
    }
}