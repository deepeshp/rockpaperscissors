package com.poojalakhani27.rockpaperscissors.domain;

/**
 * A player of the game.
 * A player has a capability to perform the gesture
 *
 * @author Pooja Lakhani
 */
public interface Player {
    void setGesture(Gesture gesture);
    String getName();
    Gesture doGesture();
}
