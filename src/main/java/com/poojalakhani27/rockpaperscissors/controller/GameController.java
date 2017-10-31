package com.poojalakhani27.rockpaperscissors.controller;

import com.poojalakhani27.rockpaperscissors.dto.AddRoundRequest;
import com.poojalakhani27.rockpaperscissors.dto.RoundDTO;
import com.poojalakhani27.rockpaperscissors.service.GameService;
import com.poojalakhani27.rockpaperscissors.exception.InvalidRoundRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableAutoConfiguration
@RequestMapping("v1/game")
public class GameController {
    private GameService gameService;

    @Value( "${invalid.input.message}" )
    private String invalidInputExceptionMessage;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/round", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    private RoundDTO addARound(@RequestBody AddRoundRequest addRoundRequest) {
        if(addRoundRequest.getHumanGesture() == null)
            throw new HttpMessageNotReadableException(invalidInputExceptionMessage);
        final RoundDTO roundDTO = gameService.playRound(addRoundRequest.getHumanGesture());
        return roundDTO;
    }

    @ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRoundRequestException.class)
    public HttpEntity onInvalidRoundRequest(HttpServletRequest req, Exception ex) {
       return new HttpEntity(ex.getMessage());
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HttpEntity onHttpMessageNotReadableException() {
        return new HttpEntity(invalidInputExceptionMessage);
    }
}
