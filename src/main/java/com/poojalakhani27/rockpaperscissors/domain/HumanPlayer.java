package com.poojalakhani27.rockpaperscissors.domain;

/**
 * Represents a Human player. Gestures need to be set on this object.
 *
 * @author Pooja Lakhani
 */
public class HumanPlayer implements Player {
    private Gesture gesture;
    private final String name;

    public HumanPlayer() {
        name = "Human Player";
    }

    @Override
    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Gesture doGesture() {
        return gesture;
    }

}
