package com.poojalakhani27.rockpaperscissors;

import com.poojalakhani27.rockpaperscissors.domain.ComputerPlayer;
import com.poojalakhani27.rockpaperscissors.domain.Gesture;

public class MockComputerPlayer extends ComputerPlayer {

    private Gesture gesture;

    @Override
    public Gesture doGesture() {
        return gesture;
    }

    public MockComputerPlayer(Gesture gesture) {
        this.gesture = gesture;
    }

    public MockComputerPlayer() {
        this.gesture = Gesture.ROCK;
    }
}
