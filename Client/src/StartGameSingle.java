package org.boes.praktikum.gameclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartGameSingle {
    int p1bet;

    public int getP1bet() {
        return p1bet;
    }

    public void setP1bet(int p1bet) {
        this.p1bet = p1bet;
    }

}
