package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.Gesture;

import java.util.Optional;

public interface RulesEngine {
    Optional<Gesture> applyRules(Gesture p1Gesture, Gesture p2Gesture);
}
