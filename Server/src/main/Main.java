package org.boes.praktikum;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

import java.time.LocalDateTime;

import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] f) {

        Javalin app = Javalin.create().start(7070);
        app.get("/e", ctx -> ctx.result("Hello World"));



        app.post("/start", ctx -> {
            Status sta = StartController.startGame(ctx.body());
            if(sta != null)
                ctx.json(sta);
        });
        app.post("/hit", ctx -> {
           Status sta = HitController.hitPly(ctx.body());
            ctx.json(sta);

        }); app.get("/status", ctx -> {

            if(Game.game.standPlayer1 && Game.game.standPlayer2)
                ctx.json(StandController.st);
            else
                ctx.json(new Status());

        });
        app.post("/stand", ctx -> {
          Status sta = StandController.stand(ctx.body());





            ctx.json(sta);

        });



    }

}
