package com.example.jokubas.snookertracker_1601768;

/**
 * Created by jokubas on 27/01/18.
 */
public class ScoreTrackSingle extends ScoreTrack {


    /**
     * Instantiates a new Score track single.
     *
     * @param player1 the player 1 name
     * @param player2 the player 2 name
     */
    public ScoreTrackSingle(String player1, String player2) {
        super();
        this.player1T1 = player1;
        this.player1T2 = player2;
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
        setAvailabaleBalls();
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
        setAvailabaleBalls();
    }

    public int mapTurnToPlayerImageID() {
        switch (turn) {
            case P1T1:
                return R.id.p1_image;
            case P1T2:
                return R.id.p2_image;
            default:
                return 0;
        }
    }

}
