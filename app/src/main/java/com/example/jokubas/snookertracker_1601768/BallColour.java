package com.example.jokubas.snookertracker_1601768;

/**
 * Enum for keeping track the score of
 * each ball in the snooker game
 */
public enum BallColour {
    RED(1),
    YELLOW(2),
    GREEN(3),
    BROWN(4),
    BLUE(5),
    PINK(6),
    BLACK(7);

    private final int value;

    BallColour(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
