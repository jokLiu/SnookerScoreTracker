package com.example.jokubas.snookertracker_1601768;

import java.util.Arrays;

/**
 * Created by jokubas on 27/01/18.
 */

public class ScoreTracker {

    private static int FOUL = 4;
    private int team1Score;
    private int team2Score;
    private int player1_team1Score;
//    private int player2_team1Score;
    private int player1_team2Score;
//    private int player2_team2Score;
    private String player1T1;
    private String player2T1;
    private String player1T2;
    private String plyaer2T2;
    private boolean isTeamGame;
    private Turn turn;
    private int countRed;
    private boolean wasRedPotted;
    private boolean[] balls;

    public ScoreTracker(String player1, String player2) {
        this.isTeamGame = false;
        this.player1T1 = player1;
        this.player1T2 = player2;
        this.player1_team1Score = 0;
        this.player1_team2Score = 0;
        this.countRed = 15;
        this.wasRedPotted = false;
        this.balls = new boolean[8];
        this.turn = Turn.P1T1;
        Arrays.fill(balls, false);
    }

    public ScoreTracker(String player1_team1, String player2_team1,
                        String player1_team2, String player2_team2) {
        // TODO
    }


    public int getPlayer1T1Score() {
        return player1_team1Score;
    }

    public int getPlayer1T2Score() {
        return player1_team2Score;
    }

    public void updateScore(BallColour ball) {

        if (countRed == 0) {
            updateScoreColoured(ball);
            return;
        }

        if (wasRedPotted && ball == BallColour.RED) {
            foul();
            return;
        }

        if (ball == BallColour.RED) {
            countRed--;
            wasRedPotted = true;
        } else {
            if (!wasRedPotted) {
                foul();
                return;
            }
            wasRedPotted=false;
        }
        addValue(ball);

        if (countRed == 0) {
            balls[BallColour.RED.getValue()] = true;
        }
    }

    private void updateScoreColoured(BallColour ball) {
        if (balls[ball.getValue() - 1]) {
            addValue(ball);
            balls[ball.getValue()] = true;
        } else {
            foul();
        }
    }


    private void addValue(BallColour ball) {
        switch (turn) {
            case P1T1:
                player1_team1Score += ball.getValue();
                break;
            case P1T2:
                player1_team2Score += ball.getValue();
                break;
            case P2T1:
//                player2_team1Score += ball.getValue();
                break;
            case P2T2:
//                player2_team2Score += ball.getValue();
                break;
        }
    }

    public void foul() {
        if (!isTeamGame) {
            switch (turn) {
                case P1T1:
                    player1_team2Score += FOUL;
                    turn = Turn.P1T2;
                    break;
                case P1T2:
                    player1_team1Score += FOUL;
                    turn = Turn.P1T1;
                    break;
            }
        }
        wasRedPotted = false;
    }

    public void nextPlayer() {
        if (!isTeamGame) {
            switch (turn) {
                case P1T1:
                    turn = Turn.P1T2;
                    break;
                case P1T2:
                    turn = Turn.P1T1;
                    break;
            }
        }
        wasRedPotted = false;
    }


}
