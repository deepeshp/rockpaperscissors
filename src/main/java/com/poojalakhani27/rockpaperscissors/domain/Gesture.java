package com.poojalakhani27.rockpaperscissors.domain;


import java.util.Random;

/**
 * Represents gesture instances i.e. {@code ROCK} or {@code PAPER} or {@code SCISSORS}.
 *
 * @author Pooja Lakhani
 */
public enum Gesture {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);

    private Integer gestureNumberNotation;

    Gesture(Integer i) {
        gestureNumberNotation = i;
    }

    private static final Random random =  new Random();

    private static Gesture from(int gestureNotation) {
        for (Gesture gesture : Gesture.values()) {
            if (gesture.getGestureNumberNotation().equals(gestureNotation))
                return gesture;
        }
        return null;
    }

    public static Gesture randomGesture() {
        final int i = random.nextInt(Gesture.values().length);
        return from(i);
    }

    private Integer getGestureNumberNotation() {
        return gestureNumberNotation;
    }

}
