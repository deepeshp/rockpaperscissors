package com.poojalakhani27.rockpaperscissors;

import com.poojalakhani27.rockpaperscissors.domain.HumanPlayer;
import com.poojalakhani27.rockpaperscissors.domain.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestBeansConfiguration {

    @Bean
    public Player player1() {
        return new HumanPlayer();
    }

    @Bean(name = "player2")
    public Player player2() {
        return new MockComputerPlayer();
    }
}
