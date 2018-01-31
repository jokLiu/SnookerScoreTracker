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
        balls = new boolean[8];
        Arrays.fill(balls, false);
        this.player1_team1Score = 0;
        this.player1_team2Score = 0;
        availBalls = new boolean[8];
        Arrays.fill(availBalls, false);
        setAvailabaleBalls();
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
            for (int i = balls.length-2; i>0; i--) {
                if (balls[i]){
                    availBalls[i+1] = true;
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
            setAvailabaleBalls();
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
        setAvailabaleBalls();
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

    public abstract int mapTurnToPlayerImageID();

    public int mapBallColourToButtonID(BallColour ball) {
        switch (ball) {
            case RED:
                return R.id.red;
            case YELLOW:
                return R.id.yellow;
            case GREEN:
                return R.id.green;
            case BROWN:
                return R.id.brown;
            case BLUE:
                return R.id.blue;
            case PINK:
                return R.id.pink;
            case BLACK:
                return R.id.black;
        }

        // should never happen
        return 0;
    }
}
