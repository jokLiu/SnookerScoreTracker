package com.example.jokubas.snookertracker_1601768;

import java.util.Arrays;

/**
 * Created by jokubas on 31/01/18.
 */

public abstract class ScoreTrack {


    protected String player1T1;
    protected String player1T2;

    // all
    protected static int FOUL = 4;
    protected Turn turn;
    protected int countRed;
    protected boolean wasRedPotted;
    protected boolean[] balls;
    protected int player1_team1Score;
    protected int player1_team2Score;
    protected boolean[] availBalls;

    public ScoreTrack(){
        this.turn = Turn.P1T1;
        this.countRed = 15;
        this.wasRedPotted = false;
        this.player1_team1Score = 0;
        this.player1_team2Score = 0;
        this.balls = new boolean[8];
        Arrays.fill(balls, false);
    }

    public boolean checkGameEnded() {
        return balls[7];
    }

    public int getPlayer1T1Score() {
        return player1_team1Score;
    }

    public int getPlayer1T2Score() {
        return player1_team2Score;
    }


    protected void setAvailabaleBalls() {
        Arrays.fill(availBalls, false);
        if (countRed == 0) {
            for (int i = 0; i < balls.length; i++) {
                if (balls[i]) {
                    availBalls[i] = true;
                    return;
                }
            }
        }
        if (wasRedPotted) {
            Arrays.fill(availBalls, BallColour.RED.getValue() + 1,
                    availBalls.length, true);
        } else {
            availBalls[BallColour.RED.getValue()] = true;
        }
    }

    public boolean[] getAvailableBalls() {
        return availBalls;
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
            wasRedPotted = false;
        }
        addValue(ball);

        if (countRed == 0) {
            balls[BallColour.RED.getValue()] = true;
        }
    }

    protected void updateScoreColoured(BallColour ball) {
        if (balls[ball.getValue() - 1]) {
            addValue(ball);
            balls[ball.getValue()] = true;
        } else {
            foul();
        }
    }

    protected abstract void addValue(BallColour ball);

    public abstract void foul();

    public abstract void nextPlayer();

    public abstract int mapTurnToPlayerImageID(Turn turn);
}
