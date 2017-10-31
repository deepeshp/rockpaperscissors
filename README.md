This is MVP version of game backend developed using Spring Boot. 

Design approach :
*****************

The solution is designed keeping in mind the following factors :
1. extensibility of game for n-gestures i.e. Rock-Paper-Scissors-Lizard-Spock
2. Minimal changes to the code when the behavior/rules of and gesture changes. e.g if the rules are changed to paper can cover the scissors, scissors can break the rock and rock can tear the paper.

Architecture :
**************
RockPaperScissorsIntegrationTest runs all the scenarios of the game.

A Test Driven Development is adopted with the following considerations :

1. The decoupling of game rules from game is done by RulesEngine.
2. The game can be extended to, say a five Gesture game (Rock-Paper-Scissors-Lizard-Spock) by just injecting the implementation of Five Gesture Rules Engine.
3. Game over condition is externalised as a Predicate. So that it is decoupled from RoundResult.
4. Players are abstracted in Game instance. So that the game can be played by any 2 players irrespective of them being Human or Computer.
5. Messages and Configurations are externalized in properties files.
6. The threshold number of winnings required to make the game over can be configured.
7. Immutable Domain Objects e.g. Round, Game, Player, RulesEngine etc.
8. A Round is a sub-resource of Game.
9. Data Transfer Objects (DTOs) are used to expose only the required domain data.
10. 422-UnProcessed Entity when round is requested for a game that is already over

Assumptions :
*************
1. The game is designed for only 2 players because it is a Zero-Sum game irrespective of any number of Gestures.
2. A singleton instance of game is maintained per application context. This way is chosen so that states of multiple games will not have to be maintained.

How to run :
************
java -jar ./target/rockpaperscissors-1.0.jar


