package com.example.jokubas.snookertracker_1601768;

/**
 * Created by jokubas on 27/01/18.
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
