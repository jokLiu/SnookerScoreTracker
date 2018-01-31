package com.example.jokubas.snookertracker_1601768;

import java.util.Arrays;

/**
 * Created by jokubas on 31/01/18.
 */

public class ScoreTrackMulti extends ScoreTrack {


    private int team1Score;
    private int team2Score;
    private int player2_team1Score;
    private int player2_team2Score;
    private String player2T1;
    private String player2T2;


    public ScoreTrackMulti(String player1_team1, String player2_team1,
                           String player1_team2, String player2_team2) {
        // TODO
    }


    public int getPlayer1T1Score() {
        return player1_team1Score;
    }

    public int getPlayer1T2Score() {
        return player1_team2Score;
    }


    public int getPlayer2T1Score() {
        return player2_team1Score;
    }

    public int getPlayer2T2Score() {
        return player2_team2Score;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }


    protected void addValue(BallColour ball) {
        int value = ball.getValue();
        switch (turn) {
            case P1T1:
                player1_team1Score += value;
                team1Score += value;
                break;
            case P1T2:
                player1_team2Score += value;
                team2Score += value;
                break;
            case P2T1:
                player2_team1Score += value;
                team1Score += value;
                break;
            case P2T2:
                player2_team2Score += value;
                team2Score += value;
                break;
        }
    }

    public void foul() {
        switch (turn) {
            case P1T1:
            case P2T1:
                team2Score += FOUL;
                break;
            case P1T2:
            case P2T2:
                team1Score += FOUL;
                break;
        }
        nextPlayer();
        wasRedPotted = false;
    }

    public void nextPlayer() {
        switch (turn) {
            case P1T1:
                turn = Turn.P1T2;
                break;
            case P1T2:
                turn = Turn.P2T1;
                break;
            case P2T1:
                turn = Turn.P2T2;
                break;
            case P2T2:
                turn = Turn.P1T1;
                break;
        }
        wasRedPotted = false;
    }

    public int mapTurnToPlayerImageID(Turn turn) {
        switch (turn) {
            case P1T1:
                return R.id.p1_t1_image;
            case P2T1:
                return R.id.p2_t1_image;
            case P1T2:
                return R.id.p3_t2_image;
            case P2T2:
                return R.id.p4_t2_image;
        }
        return 0;
    }
}
