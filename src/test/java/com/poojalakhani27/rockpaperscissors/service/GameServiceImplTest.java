package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.*;
import com.poojalakhani27.rockpaperscissors.dto.RoundDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameServiceImplTest {
    @Mock
    private Game game;
    @Mock
    private Round round;
    @Mock
    private Player player1;
    @Mock
    private Player player2;

    private GameService gameService;

    @Before
    public void setUp()  {
        initMocks(this);
        gameService = new GameServiceImpl(game);
    }

    @Test
    public void shouldReturnCorrespondingDTOForRound() {
        GameStatus gameStatus = GameStatus.inProgress();
        Gesture rock = Gesture.ROCK;
        String p1Name = "P1 name";
        String p2Name = "P2 name";
        when(game.playARound()).thenReturn(round);
        when(round.getP1Gesture()).thenReturn(rock);
        when(round.getP2Gesture()).thenReturn(rock);
        when(round.getPlayer1()).thenReturn(player1);
        when(round.getPlayer2()).thenReturn(player2);
        when(player1.getName()).thenReturn(p1Name);
        when(player2.getName()).thenReturn(p2Name);
        when(game.getGameStatus()).thenReturn(gameStatus);
        when(round.getResult()).thenReturn(RoundResult.draw());

        RoundDTO roundDTO = gameService.playRound(rock);

        RoundDTO expectedRountDTO = new RoundDTO(p1Name, p2Name, rock, rock, RoundResult.draw(), gameStatus);
        assertEquals(expectedRountDTO, roundDTO);
    }


}