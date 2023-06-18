package org.boes.praktikum;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {
    public BlackJackGame(int coinsPlayer1, int coinsPlayer2) {
        this.coinsPlayer1 = coinsPlayer1;
        this.coinsPlayer2 = coinsPlayer2;
    }

    //Lists to hold cards in players' and dealer's hands
    List<Card> CardsPlayer1 = new ArrayList<>();
    List<Card> CardsPlayer2 = new ArrayList<>();
    List<Card> Dealer = new ArrayList<>();
    //2 integers to store players bets
    int coinsPlayer1;
    int coinsPlayer2;
    boolean standPlayer1 = false;
    boolean standPlayer2 = false;
    //Creating a deck of cards
    Deck deck = new Deck();
    //index used to take cards from the Deck consequetivly
    int index = 0;

    /**
     * executes the initial steps of the game
     */
    public void start(){
        // shuffle the cards to start the game ;
        Collections.shuffle(deck.cards);
        Dealer.add(deck.cards.get(index));
        ++index;
        Dealer.add(deck.cards.get(index));
        ++index;
        CardsPlayer1.add(deck.cards.get(index));
        ++index;
        CardsPlayer1.add(deck.cards.get(index));
        ++index;
        CardsPlayer2.add(deck.cards.get(index));
        ++index;
        CardsPlayer2.add(deck.cards.get(index));
        index++;

    }
    // function to give a player a card in case he or she asks for more cards
    public void hit(int player){
        if(player == 1){
            CardsPlayer1.add(deck.cards.get(index));
            index++;
        }    else {
            CardsPlayer2.add(deck.cards.get(index));
            index++;
        }
    }

    /**
     * the function changes the boolean standplayer if the player wanted to stand
     * @param player
     */
    public void stand(int player){
        if(player == 1){
          standPlayer1 = true;
        }    else {
            standPlayer2 = true;
        }




    }

    /**
     * returns the total sum of points that a player has
     * @param player
     * @return
     */
    public int sumTotalPoints(int player){
        int sum = 0;
        if(player == 1){
            for(int i = 0; i < CardsPlayer1.size(); i++){
                sum = sum + CardsPlayer1.get(i).value;
            }
        }    else if(player == 2) {
            for(int i = 0; i < CardsPlayer2.size(); i++){
                sum = sum + CardsPlayer2.get(i).value;
            }
        }else {
            for(int i = 0; i < Dealer.size(); i++){
                sum = sum + Dealer.get(i).value;
            }
        }
        return sum;
    }

    /**
     *this function finalizes the game
     * @param sta
     */
    public void finalStep(Status sta) {
         if(Game.game.standPlayer2 && Game.game.standPlayer1){
        while(sumTotalPoints(0) < 17){
            Game.game.Dealer.add(deck.cards.get(index));
            index++;
        };
        if(!sta.p1lost && sumTotalPoints(0) < sumTotalPoints(1) ||(sumTotalPoints(0) >21 && !sta.p1lost) ){
            sta.p1guthaben += Game.game.coinsPlayer1 * 2;
            sta.p1won = true;
            Game.game.coinsPlayer1= 0;
            sta.p1bet = 0;
        }
        else if (sumTotalPoints(0) == sumTotalPoints(1)) {
            sta.p1equal = true;
            sta.p1guthaben += Game.game.coinsPlayer1 * 2;
            Game.game.coinsPlayer1= 0;
            sta.p1bet = 0;




        } else {
            sta.p1lost = true;
            sta.p1bet = 0;

        }
        if((!sta.p2lost && sumTotalPoints(0) < sumTotalPoints(2) )||(sumTotalPoints(0) >21 && !sta.p2lost) ){
            sta.p2guthaben += Game.game.coinsPlayer2 * 2;
            sta.p2won = true;
            sta.p2bet = 0;

            Game.game.coinsPlayer2 = 0;
        }   else if(sumTotalPoints(0) == sumTotalPoints(2) ){
            sta.p2equal = true;
            sta.p2bet = 0;
            sta.p2guthaben += Game.game.coinsPlayer2 * 2;
            Game.game.coinsPlayer2 = 0;




        }else
            {
                sta.p2lost = true;
                sta.p2bet = 0;

            }

             sta.Dealer = "Dealer's Cards";
        for(int i = 0; i < Game.game.Dealer.size(); i++){
            sta.Dealer = sta.Dealer+" " + Game.game.Dealer.get(i).name;
        }


    }
         sta.dealersum = sumTotalPoints(3);
         StartController.bet2 = 0;
         StartController.bet1 = 0;

    }
}
