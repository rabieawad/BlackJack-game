package org.boes.praktikum.gameclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartGame {
    int bet;
    int player;

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int getBet() {
        return bet;
    }


    public void setBet(int  bet) {
        this.bet =  bet;
    }


}
