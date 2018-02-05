package com.example.jokubas.snookertracker_1601768;

import java.util.Arrays;

/**
 * Main class for keeping track the score of the game
 */
public abstract class ScoreTrack {


    /**
     * The constant FOUL.
     */
    protected static int FOUL = 4;
    /**
     * The Player 1 Team 1.
     */
    protected String player1T1;
    /**
     * The Player 1 Team 2.
     */
    protected String player1T2;
    /**
     * The Turn of the current player.
     */
    protected Turn turn;
    /**
     * The Count red balls remaining on the table.
     */
    protected int countRed;
    /**
     * The check if the red ball was potted.
     */
    protected boolean wasRedPotted;
    /**
     * The balls which are still on the table.
     */
    protected boolean[] balls;
    /**
     * The Player 1 team 1 score.
     */
    protected int player1_team1Score;
    /**
     * The Player 1 team 2 score.
     */
    protected int player1_team2Score;
    /**
     * The Available balls to put in the current turn.
     */
    protected boolean[] availBalls;


    /**
     * Instantiates a new Score track.
     */
    public ScoreTrack() {
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

    /**
     * Gets number of red balls.
     *
     * @return number of reds
     */
    public int getNumberOfReds() {
        return countRed;
    }

    /**
     * Check whether the game ended.
     *
     * @return true if game ended.
     */
    public boolean checkGameEnded() {
        return balls[7];
    }

    /**
     * Gets player 1 team 1 score.
     *
     * @return the player 1 team 1 score
     */
    public int getPlayer1T1Score() {
        return player1_team1Score;
    }

    /**
     * Gets player 1 team 2 score.
     *
     * @return the player 1 team 2 score
     */
    public int getPlayer1T2Score() {
        return player1_team2Score;
    }


    /**
     * Gets player 1 team 1 name.
     *
     * @return the player 1 team 1 name
     */
    public String getPlayer1T1Name() {
        return player1T1;
    }

    /**
     * Gets player 1 team 2 name.
     *
     * @return the player 1 team 2 name
     */
    public String getPlayer1T2Name() {
        return player1T2;
    }


    /**
     * Sets availabale balls for the current turn.
     */
    protected void setAvailabaleBalls() {
        Arrays.fill(availBalls, false);
        if (countRed == 0) {
            for (int i = balls.length - 2; i > 0; i--) {
                if (balls[i]) {
                    availBalls[i + 1] = true;
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

    /**
     * Get available balls for the current turn.
     *
     * @return available balls
     */
    public boolean[] getAvailableBalls() {
        return availBalls;
    }

    /**
     * Update the score of the game.
     *
     * @param ball the ball which was potted.
     */
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

    /**
     * Update score with coloured balls.
     *
     * @param ball the ball
     */
    protected void updateScoreColoured(BallColour ball) {
        if (balls[ball.getValue() - 1]) {
            addValue(ball);
            balls[ball.getValue()] = true;
        } else {
            foul();
        }
    }

    /**
     * Add value to the current player.
     *
     * @param ball the ball which was potted
     */
    protected abstract void addValue(BallColour ball);

    /**
     * Foul to record for current player.
     */
    public abstract void foul();

    /**
     * Next player.
     * <p>
     * Change the active player.
     */
    public abstract void nextPlayer();

    /**
     * Map the Turn to the currently active player's image id in the layout.
     *
     * @return the image id
     */
    public abstract int mapTurnToPlayerImageID();

    /**
     * Map ball colour to button id.
     *
     * @param ball the ball
     * @return the image id
     */
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
