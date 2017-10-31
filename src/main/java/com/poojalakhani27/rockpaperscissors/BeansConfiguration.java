package com.poojalakhani27.rockpaperscissors;

import com.poojalakhani27.rockpaperscissors.domain.HumanPlayer;
import com.poojalakhani27.rockpaperscissors.domain.Player;
import com.poojalakhani27.rockpaperscissors.domain.ComputerPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class BeansConfiguration {
    @Bean
    public Player player1() {
        return new HumanPlayer();
    }

    @Bean
    public Player player2() {
        return new ComputerPlayer();
    }
}
