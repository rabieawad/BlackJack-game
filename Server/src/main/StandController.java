package org.boes.praktikum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StandController {
    public static Status st;

    /**
     * this controller recieves stand requests and processes them and does the required changes
     * and the game is finalized when both players have sent stand requests
     * @param body
     * @return
     */
    public static Status stand(String body){
        ObjectMapper objectMapper = new ObjectMapper();
        StandRequest stand = null;
        try {
            stand = objectMapper.readValue( body , StandRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Game.game.stand (stand.player);
        Status sta = new  Status();
        if(Game.game.standPlayer1 && Game.game.standPlayer2) {
            Game.game.finalStep(sta);
            st = sta;
        }

        return sta;
    }
}
