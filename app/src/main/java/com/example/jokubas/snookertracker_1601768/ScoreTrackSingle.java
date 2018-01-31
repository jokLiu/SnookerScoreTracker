package com.example.jokubas.snookertracker_1601768;

import java.util.Arrays;

/**
 * Created by jokubas on 27/01/18.
 */

public class ScoreTrackSingle extends ScoreTrack {

    public ScoreTrackSingle(String player1, String player2) {
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


    @Override
    public boolean[] getAvailableBalls() {
            return new boolean[0];
    }

    public void addValue(BallColour ball) {
        switch (turn) {
            case P1T1:
                player1_team1Score += ball.getValue();
                break;
            case P1T2:
                player1_team2Score += ball.getValue();
                break;
            default:
                break;
        }
    }

    public void foul() {
        switch (turn) {
            case P1T1:
                player1_team2Score += FOUL;
                break;
            case P1T2:
                player1_team1Score += FOUL;
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
                turn = Turn.P1T1;
                break;
        }
        wasRedPotted = false;
    }

    public int mapTurnToPlayerImageID(Turn turn) {
        switch (turn) {
            case P1T1:
                return R.id.p1_image;
            case P2T1:
                return R.id.p2_image;
            default:
                return 0;
        }
    }
}
