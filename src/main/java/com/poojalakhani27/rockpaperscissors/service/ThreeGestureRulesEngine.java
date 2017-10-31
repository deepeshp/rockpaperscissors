package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import com.poojalakhani27.rockpaperscissors.domain.Tuple;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ThreeGestureRulesEngine implements RulesEngine {
    private static Map<Tuple<Gesture>, Gesture> rules;
    {
        initializeRules();
    }
    private static void initializeRules() {
        rules = new HashMap<>();
        rules.put(new Tuple<>(ROCK, PAPER), PAPER);
        rules.put(new Tuple<>(PAPER, PAPER), null);
        rules.put(new Tuple<>(ROCK, SCISSORS), ROCK);
        rules.put(new Tuple<>(ROCK, ROCK), null);
        rules.put(new Tuple<>(PAPER, SCISSORS), SCISSORS);
        rules.put(new Tuple<>(SCISSORS, SCISSORS), null);
    }

    @Override
    public Optional<Gesture> applyRules(Gesture p1Gesture, Gesture p2Gesture) {
        Gesture gesture = rules.get(new Tuple<>(p1Gesture, p2Gesture));
        return Optional.ofNullable(gesture);
    }
}
