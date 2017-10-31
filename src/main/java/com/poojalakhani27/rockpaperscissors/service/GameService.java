package com.poojalakhani27.rockpaperscissors.service;

import com.poojalakhani27.rockpaperscissors.domain.Gesture;
import com.poojalakhani27.rockpaperscissors.dto.RoundDTO;

public interface GameService {
    RoundDTO playRound(Gesture gesture);
}
