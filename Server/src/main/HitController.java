package org.boes.praktikum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HitController {
    /**
     * this controller recieves hit requests and processes them and does the required changes
     * @param body
     * @return
     */
    static Status hitPly(String body){
        ObjectMapper objectMapper = new ObjectMapper();
        HitRequest hit = null;
        try {
            hit = objectMapper.readValue(body, HitRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Game.game.hit(hit.player);
        Status sta = new Status();
        if(sta.p1lost == true){
            Game.game.coinsPlayer1 = 0;
            StartController.bet1 = 0;
         }
        if(sta.p2lost == true){
             Game.game.coinsPlayer2 = 0;
             StartController.bet2 = 0;
        }
        return  sta ;
    }
}
