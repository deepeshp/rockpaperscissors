package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ThreeGestureRulesEngineTest {
    private ThreeGestureRulesEngine rulesEngine;

    @Before
    public void setUp() throws Exception {
        rulesEngine = new ThreeGestureRulesEngine();
    }

    @Test
    public void whenRockVsScissors_RockWins() {
        Optional<Gesture> expectedWinner = Optional.of(Gesture.ROCK);

        Optional<Gesture> actualWinner = rulesEngine.applyRules(Gesture.ROCK, Gesture.SCISSORS);
        assertEquals(expectedWinner, actualWinner);

        actualWinner = rulesEngine.applyRules(Gesture.SCISSORS, Gesture.ROCK);
        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    public void whenRockVsPaper_PaperWins() {
        Optional<Gesture> expectedWinner = Optional.of(Gesture.PAPER);

        Optional<Gesture> actualWinner = rulesEngine.applyRules(Gesture.ROCK, Gesture.PAPER);
        assertEquals(expectedWinner, actualWinner);

        actualWinner = rulesEngine.applyRules(Gesture.PAPER, Gesture.ROCK);
        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    public void whenRockVsRock_Draw() {
        Optional<Gesture> expectedWinner = Optional.empty();

        Optional<Gesture> actualWinner = rulesEngine.applyRules(Gesture.ROCK, Gesture.ROCK);
        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    public void whenPaperVsPaper_Draw() {
        Optional<Gesture> expectedWinner = Optional.empty();

        Optional<Gesture> actualWinner = rulesEngine.applyRules(Gesture.ROCK, Gesture.ROCK);
        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    public void whenScissorsVsScissors_Draw() {
        Optional<Gesture> expectedWinner = Optional.empty();

        Optional<Gesture> actualWinner = rulesEngine.applyRules(Gesture.SCISSORS, Gesture.SCISSORS);
        assertEquals(expectedWinner, actualWinner);
    }
}