package com.poojalakhani27.rockpaperscissors.domain;

/**
 * Represents a Bot player that does random gestures. Gestures cannot be set on this Object
 *
 *  @author Pooja Lakhani
 */
public class ComputerPlayer implements Player {

    @Override
    public void setGesture(Gesture gesture) {
        // No op
    }

    @Override
    public String getName() {
        return "Computer Player";
    }

    @Override
    public Gesture doGesture() {
        return Gesture.randomGesture();
    }

}
