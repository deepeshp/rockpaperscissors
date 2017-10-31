package com.poojalakhani27.rockpaperscissors.domain;

import com.poojalakhani27.rockpaperscissors.exception.InvalidRoundRequestException;
import com.poojalakhani27.rockpaperscissors.service.Predicate;
import com.poojalakhani27.rockpaperscissors.service.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates the rock paper scissors game behavious. Each instance holds a snapshot of the game.
 * Multiple {@code Round}s can be added to a single game
 *
 * {@code RulesEngine} should be injected to know the {@code Round} evaluation.
 * A {@code Predicate} that determines whether a game is over, needs to be injected.
 *
 * @author Pooja Lakhani
 */
@Component
public class Game {
    private final Player player1;
    private final Player player2;
    private final Map<Player, Integer> winCount;
    private final RulesEngine rulesEngine;
    private final Predicate<Game> gameOverPredicate;
    private GameStatus gameStatus;

    @Value( "${invalidroundrequest.message}" )
    private String inValidRoundRequestMessage;

    @Autowired
    public Game(Player player1, @Qualifier("player2") Player player2, RulesEngine rulesEngine, Predicate<Game> gameOverPredicate) {
        this.player1 = player1;
        this.player2 = player2;
        this.rulesEngine = rulesEngine;
        this.gameOverPredicate = gameOverPredicate;
        winCount = new HashMap<>();
        initializeGameState();
    }

    private void initializeGameState() {
        winCount.put(player1, 0);
        winCount.put(player2, 0);
        gameStatus = GameStatus.inProgress();
    }

    public Round playARound() {
        if(gameOverPredicate.apply(this))
            throw new InvalidRoundRequestException(inValidRoundRequestMessage);

        Round round = new Round(player1, player2, rulesEngine);

        gameStatus = gameStatusFrom(round.getResult());

        return round;

    }

    private GameStatus gameStatusFrom(RoundResult roundResult) {
            GameStatus gameStatus;
            if(roundResult.isResolved()) {
                Player winner = roundResult.getWinner();
                incrementWinCount(winner);
                gameStatus = transitionToOverIfNeeded(winner);
            } else {
                gameStatus =  GameStatus.inProgress();
            }

            return gameStatus;
    }

    private GameStatus transitionToOverIfNeeded(Player winner) {
        GameStatus gameStatus;
        if(gameOverPredicate.apply(this))
            gameStatus = GameStatus.over(winner);
        else
            gameStatus = GameStatus.inProgress();
        return gameStatus;
    }

    private void incrementWinCount(Player player) {
        Integer count = winCount.get(player);
        winCount.put(player, ++count);
    }

    public void addHumanGesture(Gesture gesture) {
        player1.setGesture(gesture);
    }

    public int getPlayer1Winnings() {
        return winCount.get(player1);
    }

    public int getPlayer2Winnings() {
        return winCount.get(player2);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getHumanPlayer() {
        return player1;
    }

    public Player getComputerPlayer() {
        return player2;
    }
}
