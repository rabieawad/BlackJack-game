package org.boes.praktikum.gameclient;


import java.util.ArrayList;
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
    public void stand(int player){
        if(player == 1){
          standPlayer1 = true;
        }    else {
            standPlayer2 = true;
        }




    }
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

    public void finalStep(Status sta) {

    }
}
