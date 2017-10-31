package com.poojalakhani27.rockpaperscissors.domain;

import com.poojalakhani27.rockpaperscissors.service.RulesEngine;

import java.util.Optional;

/**
 * Represents a round of a game.
 * It encapsulates the outcome of a round evaluation in the form of {@code RoundResult}
 * If the {@code RulesEngine} returns a winner, round is {@code RESOLVED} else is {@code DRAW}.
 *
 * @author Pooja Lakhani
 */
public class Round {
    private final Player p1;
    private final Player p2;
    private final Gesture p1Gesture;
    private final Gesture p2Gesture;
    private final RulesEngine rulesEngine;
    private RoundResult result;

    public Round(Player p1, Player p2, RulesEngine rulesEngine) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1Gesture = p1.doGesture();
        this.p2Gesture = p2.doGesture();
        this.rulesEngine = rulesEngine;

        evaluate();
    }


    private void  evaluate() {
        Optional<Gesture> winningGesture = rulesEngine.applyRules(p1Gesture, p2Gesture);

        if(winningGesture.isPresent())
            result =  RoundResult.resolved(getPlayerFor(winningGesture.get()));
        else
            result = RoundResult.draw();
    }

    private Player getPlayerFor(Gesture gesture) {
         if(gesture.equals(p1Gesture))
             return p1;
        else
            return p2;

    }

    public RoundResult getResult() {
        return result;
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }

    public Gesture getP1Gesture() {
        return p1Gesture;
    }

    public Gesture getP2Gesture() {
        return p2Gesture;
    }
}
