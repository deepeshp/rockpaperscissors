package com.poojalakhani27.rockpaperscissors.domain;

import com.poojalakhani27.rockpaperscissors.MockComputerPlayer;
import com.poojalakhani27.rockpaperscissors.service.RulesEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoundTest {
    private final Gesture g1 = Gesture.ROCK;
    private final Gesture g2 = Gesture.SCISSORS;
    private Round round;
    @Mock
    private RulesEngine rulesEngine;

    @Test
    public void whenRulesEngineRetunsWinner_thenRoundResolves() {
        final Player p1 = new MockComputerPlayer(g1);
        final Player p2 = new MockComputerPlayer(g2);
        when(rulesEngine.applyRules(g1, g2)).thenReturn(Optional.of(g1));

        round = new Round(p1, p2, rulesEngine);

        assertEquals(RoundResult.resolved(p1), round.getResult());
    }

    @Test
    public void whenRulesEngineRetunsWinner_thenRoundDraws() {
        final Player p1 = new MockComputerPlayer(g1);
        final Player p2 = new MockComputerPlayer(g1);
        when(rulesEngine.applyRules(g1, g1)).thenReturn(Optional.empty());

        round = new Round(p1, p2, rulesEngine);

        assertEquals(RoundResult.draw(), round.getResult());
    }
}