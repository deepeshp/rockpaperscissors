package com.poojalakhani27.rockpaperscissors.exception;

/**
 * Represents an exception when a round request input is proper but the round cannot be processed.
 *
 * @author Pooja Lakhani
 */
public class InvalidRoundRequestException extends RuntimeException{
    public InvalidRoundRequestException(String message) {
        super(message);
    }
}
