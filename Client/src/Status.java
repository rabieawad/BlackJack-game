package org.boes.praktikum.gameclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    // Constructor

    public   String Dealer;
    public     String player1;

    public    String player2;
    public    boolean p1won  ;
    public   boolean p2won  ;
    public   boolean p1lost ;
    public   boolean p2lost  ;
    public   boolean p1equal  ;
    public   boolean p2equal  ;
    public   int p1sum;
    public   int p2sum;
    public   int p1bet;
    public   int p2bet;
    public   int dealersum;
    public  int p1guthaben = 0;
    public  int p2guthaben = 0;

}
