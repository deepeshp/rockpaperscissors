package com.poojalakhani27.rockpaperscissors;

import com.poojalakhani27.rockpaperscissors.domain.Game;
import com.poojalakhani27.rockpaperscissors.domain.GameStatus;
import com.poojalakhani27.rockpaperscissors.domain.RoundResult;
import com.poojalakhani27.rockpaperscissors.dto.AddRoundRequest;
import com.poojalakhani27.rockpaperscissors.dto.RoundDTO;
import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class RockPaperScissorsIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${game.over.winning.rounds}")
    private int thresholdWinnings;

    @Value("${invalidroundrequest.message}")
    private String message;

    @Value("${invalid.input.message}")
    private String invalidInputMessage;

    @Autowired
    private Game game;

    private static final Gesture rock = Gesture.ROCK;

    @Test
    public void when_HumanPaperVsComputerRock_thenHumanWin() {
        final Gesture humanGesture = Gesture.PAPER;
        RoundResult expectedRoundResult = RoundResult.resolved(game.getHumanPlayer());
        GameStatus expectedGameStatus = GameStatus.inProgress();
        RoundDTO expectedRountDTO = new RoundDTO("Human Player", "Computer Player", humanGesture, rock, expectedRoundResult, expectedGameStatus);

        Map actual = restTemplate.postForObject("/v1/game/round", new AddRoundRequest(humanGesture), Map.class);

        assertEquals(expectedRountDTO.toMap(), actual);
    }

    @Test
    public void when_HumanScissorsVsComputerRock_thenComputerWin() {
        final Gesture humanGesture = Gesture.SCISSORS;
        RoundResult expectedRoundResult = RoundResult.resolved(game.getComputerPlayer());
        GameStatus expectedGameStatus = GameStatus.inProgress();
        RoundDTO expectedRountDTO = new RoundDTO("Human Player", "Computer Player", humanGesture, rock, expectedRoundResult, expectedGameStatus);

        Map actual = restTemplate.postForObject("/v1/game/round", new AddRoundRequest(humanGesture), Map.class);

        assertEquals(expectedRountDTO.toMap(), actual);
    }

    @Test
    public void when_HumanRockVsComputerRock_thenDraw() {
        final Gesture humanGesture = Gesture.ROCK;
        RoundResult expectedRoundResult = RoundResult.draw();
        GameStatus expectedGameStatus = GameStatus.inProgress();
        RoundDTO expectedRountDTO = new RoundDTO("Human Player", "Computer Player", humanGesture, rock, expectedRoundResult, expectedGameStatus);

        Map actual = restTemplate.postForObject("/v1/game/round", new AddRoundRequest(humanGesture), Map.class);

        assertEquals(expectedRountDTO.toMap(), actual);
    }

    @Test
    public void whenThresholdWinningRoundsForHuman_thenGameOver() {
        final Gesture humanGesture = Gesture.PAPER;
        RoundResult expectedRoundResult = RoundResult.resolved(game.getHumanPlayer());
        GameStatus expectedGameStatus = GameStatus.over(game.getHumanPlayer());
        RoundDTO expectedRountDTO = new RoundDTO("Human Player", "Computer Player", humanGesture, rock, expectedRoundResult, expectedGameStatus);

        Map actual= null;
        actual = playNRounds(humanGesture);

        assertEquals(expectedRountDTO.toMap(), actual);
    }

    @Test
    public void whenGameOver_thenCannotAddRound() {
        final Gesture humanGesture = Gesture.PAPER;
        RoundResult expectedRoundResult = RoundResult.resolved(game.getHumanPlayer());
        GameStatus expectedGameStatus = GameStatus.over(game.getHumanPlayer());
        RoundDTO gameOverDTO = new RoundDTO("Human Player", "Computer Player", humanGesture, rock, expectedRoundResult, expectedGameStatus);
        Map actual = playNRounds(humanGesture);
        assertEquals(gameOverDTO.toMap(), actual); //asserting game over

        final ResponseEntity<String> response = restTemplate.postForEntity("/v1/game/round", new AddRoundRequest(humanGesture), String.class, (Object) null);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(message, response.getBody());
    }



    @Test
    public void whenUnrecognizedGesture_thenBadRequest() {
        final ResponseEntity<String> response = restTemplate.postForEntity("/v1/game/round", new AddRoundRequest(null), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(invalidInputMessage, response.getBody());
    }


    @Test
    public void gameStatusShouldBeInProgressAtStart() {
        assertEquals(GameStatus.inProgress(), game.getGameStatus());

    }

    private Map playNRounds(Gesture humanGesture) {
        Map actual = null;
        for(int i=1; i<=thresholdWinnings; i++) {
            actual = restTemplate.postForObject("/v1/game/round", new AddRoundRequest(humanGesture), Map.class);
        }
        return actual;
    }
}