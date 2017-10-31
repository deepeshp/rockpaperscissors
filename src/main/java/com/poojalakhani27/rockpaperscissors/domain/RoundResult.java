package com.poojalakhani27.rockpaperscissors.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Encapsulates an instance of round evaluation status.
 * {@code Player} winner is only set if the {@code RoundStatus} is RESOLVED
 *
 * @author Pooja Lakhani
 */
public class RoundResult {
    private final RoundStatus status;
    private final Player winner;

    private RoundResult(RoundStatus status, Player winner) {
        this.status = status;
        this.winner = winner;
    }

    public static RoundResult draw() {
        return new RoundResult(RoundStatus.DRAW, null);
    }

    public static RoundResult resolved(Player winner) {
        return new RoundResult(RoundStatus.RESOLVED, winner);
    }

    public boolean isDraw() {
        return RoundStatus.DRAW.equals(this.status);
    }

    public boolean isResolved() {
        return RoundStatus.RESOLVED.equals(this.status);
    }

    public RoundStatus getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(o, this);
    }

    public enum RoundStatus {
        DRAW,
        RESOLVED
    }


}
