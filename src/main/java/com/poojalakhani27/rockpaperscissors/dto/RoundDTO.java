package com.poojalakhani27.rockpaperscissors.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poojalakhani27.rockpaperscissors.domain.RoundResult;
import com.poojalakhani27.rockpaperscissors.domain.GameStatus;
import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.Map;

public class RoundDTO {
    private String player1;
    private String player2;
    private Gesture player1Gesture;
    private Gesture player2Gesture;
    private RoundResult roundResult;
    private GameStatus gameStatus;

    public RoundDTO(String player1, String player2, Gesture player1Gesture, Gesture player2Gesture, RoundResult roundResult, GameStatus gameStatus) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Gesture = player1Gesture;
        this.player2Gesture = player2Gesture;
        this.roundResult = roundResult;
        this.gameStatus = gameStatus;
    }


    public RoundResult getRoundResult() {
        return roundResult;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Gesture getPlayer1Gesture() {
        return player1Gesture;
    }

    public Gesture getPlayer2Gesture() {
        return player2Gesture;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(o, this);
    }

    public Map toMap() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Map.class);
    }
}
