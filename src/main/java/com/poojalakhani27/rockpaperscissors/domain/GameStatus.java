package com.poojalakhani27.rockpaperscissors.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.stereotype.Component;

/**
 * Encapsulates an instance of game status
 *
 * @author Pooja Lakhani
 */
@Component
public class  GameStatus {

    private Status status;
    private Player winner;

    private GameStatus(Status status, Player winner) {
        this.status = status;
        this.winner = winner;
    }

    //For Spring Inejection
    public GameStatus() {

    }

    private GameStatus(Status status) {
     this.status = status;
    }

    public static GameStatus over(Player winner) {
        return new GameStatus(Status.OVER, winner);
    }

    public static GameStatus inProgress() {
        return new GameStatus(Status.IN_PROGRESS);
    }

    public Status getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }

    public enum Status {
        IN_PROGRESS, OVER;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(o, this);
    }

}
