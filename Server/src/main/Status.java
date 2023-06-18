package org.boes.praktikum;

public class Status {
    public Status(){
        constructorFunction();


    }
   public   String Dealer = "dealer's cards: "+  Game.game.Dealer.get(0).name ;
  public     String player1 = "Player1's cards: ";

    public    String player2 = "Player2's cards: ";
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

     public void constructorFunction(){
         init();

     }
     void init(){
         p1bet = Game.game.coinsPlayer1;
         p2bet = Game.game.coinsPlayer2;
         for(int i = 0; i < Game.game.CardsPlayer1.size(); i++){
             player1 = player1 +" " +  Game.game.CardsPlayer1.get(i).name;
         }
         for(int i = 0; i < Game.game.CardsPlayer2.size(); i++){
             player2 = player2 +" " +  Game.game.CardsPlayer2.get(i).name;
         }
         if(Game.game.sumTotalPoints(1) == 21){

             p1guthaben += Game.game.coinsPlayer1 * 2;
             p1won = true;
             Game.game.coinsPlayer1 = 0;
         }
         if(Game.game.sumTotalPoints(2) == 21){
             p2bet = 0;
             p2guthaben += Game.game.coinsPlayer2 * 2;
             p2won = true;
             Game.game.coinsPlayer2 = 0;

         }if(Game.game.sumTotalPoints(1) == 21){
             p1bet = 0;
             p1guthaben += Game.game.coinsPlayer1 * 2;
             p1won = true;
             Game.game.coinsPlayer1 = 0;

         }
         if(Game.game.sumTotalPoints(1) > 21){
             p1bet = 0;
             p1lost = true;
             p1bet = 0;
             Game.game.coinsPlayer1 = 0;
         }

         if(Game.game.sumTotalPoints(2) > 21){
             p2lost = true;
             p2bet = 0;
             Game.game.coinsPlayer2 = 0;

         }


         p1sum = Game.game.sumTotalPoints(1);
         p2sum = Game.game.sumTotalPoints(2);

     }
    public int getP1bet() {
        return p1bet;
    }

    public int getP2bet() {
        return p2bet;
    }

    public void setP1bet(int p1bet) {
        this.p1bet = p1bet;
    }

    public void setP2bet(int p2bet) {
        this.p2bet = p2bet;
    }

    public  int p1guthaben = 0;
     public  int p2guthaben = 0;

}
