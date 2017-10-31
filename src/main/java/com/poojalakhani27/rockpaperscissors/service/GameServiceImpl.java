package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.Round;
import com.poojalakhani27.rockpaperscissors.dto.RoundDTO;
import com.poojalakhani27.rockpaperscissors.domain.Game;
import com.poojalakhani27.rockpaperscissors.domain.GameStatus;
import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private Game game;

    @Autowired
    public GameServiceImpl(Game game) {
        this.game = game;
    }

    @Override
    public RoundDTO playRound(Gesture gesture) {
        game.addHumanGesture(gesture);
        Round round = game.playARound();
        return roundDTO(game,  round);
    }

    private RoundDTO roundDTO(Game game, Round round) {
        GameStatus gameStatus = game.getGameStatus();
        RoundDTO roundDTO = new RoundDTO(round.getPlayer1().getName(), round.getPlayer2().getName(), round.getP1Gesture(), round.getP2Gesture(), round.getResult(),  gameStatus);
        return roundDTO;
    }
}
