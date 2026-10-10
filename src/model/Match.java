package model;

public class Match {

    private int id;
    private String team1;
    private String team2;
    private String commentary;

    public Match(int id, String team1, String team2, String commentary) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.commentary = commentary;
    }

    public int getId() {
        return id;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getCommentary() {
        return commentary;
    }

    public void display() {

        System.out.println(
            id + " - " + team1 + " vs " + team2
        );

        System.out.println(
            "   Commentary: " + commentary
        );
    }
}