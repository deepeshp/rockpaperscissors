package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A predicate to determine the condition of game over
 */
@Component
public class GameOverPredicate implements Predicate<Game> {

    @Value("${game.over.winning.rounds}")
    private int thresholdWinnings;


    @Override
    public boolean apply(Game game) {
        return thresholdWinnings == game.getPlayer1Winnings() || thresholdWinnings == game.getPlayer2Winnings();
    }
}
