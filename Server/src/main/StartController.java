package org.boes.praktikum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

import java.time.LocalDateTime;

import java.util.List;
import java.util.function.Function;
public class StartController {


    static int bet1 = 0 ;
    static int bet2 = 0;
    /**
     * this controller recieves start requests and processes them and does the required changes
     * and the game is startd when both players have sent stand requests
     * @param body
     * @return
     */

    static public Status startGame(String body ){


        ObjectMapper objectMapper = new ObjectMapper();
        StartGame s = null;
        try {
            s = objectMapper.readValue(body, StartGame.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(s.getPlayer() == 1)
            bet1 = s.bet;
        else
            bet2 = s.bet;
        if( bet1  > 0 && bet2 > 0){
            Game.game = new BlackJackGame(bet1,bet2);
            Game.game.start();
            Status sta = new Status();
            return sta;

        }

        return   null ;
    }
}
