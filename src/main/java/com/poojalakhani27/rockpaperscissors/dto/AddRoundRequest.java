package com.poojalakhani27.rockpaperscissors.dto;

import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import org.springframework.stereotype.Component;

@Component
public class AddRoundRequest {
    private Gesture humanGesture;

    public AddRoundRequest(Gesture humanGesture) {
        this.humanGesture = humanGesture;
    }

    public AddRoundRequest() {
    }

    public Gesture getHumanGesture() {
        return humanGesture;
    }

    public void setGesture(Gesture gesture) {
        this.humanGesture = gesture;
    }
}
