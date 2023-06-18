package org.boes.praktikum.gameclient;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    public Deck(){
        List<Card> cards1 = List.of(
                new Card("club-A", 10),
                new Card("club-2", 2),
                new Card("club-3", 3),
                new Card("club-4", 4),
                new Card("club-5", 5),
                new Card("club-6", 6),
                new Card("club-7", 7),
                new Card("club-8", 8),
                new Card("club-9", 9),
                new Card("club-10", 10),
                new Card("club-Q", 10),
                new Card("club-J", 10),
                new Card("club-K", 10),

                new Card("heart-A", 10),
                new Card("heart-2", 2),
                new Card("heart-3", 3),
                new Card("heart-4", 4),
                new Card("heart-5", 5),
                new Card("heart-6", 6),
                new Card("heart-7", 7),
                new Card("heart-8", 8),
                new Card("heart-9", 9),
                new Card("heart-10", 10),
                new Card("heart-Q", 10),
                new Card("heart-J", 10),
                new Card("heart-K", 10),

                new Card("diamond-A", 10),
                new Card("diamond-2", 2),
                new Card("diamond-3", 3),
                new Card("diamond-4", 4),
                new Card("diamond-5", 5),
                new Card("diamond-6", 6),
                new Card("diamond-7", 7),
                new Card("diamond-8", 8),
                new Card("diamond-9", 9),
                new Card("diamond-10", 10),
                new Card("diamond-Q", 10),
                new Card("diamond-J", 10),
                new Card("diamond-K", 10),


                new Card("spade-A", 10),
                new Card("spade-2", 2),
                new Card("spade-3", 3),
                new Card("spade-4", 4),
                new Card("spade-5", 5),
                new Card("spade-6", 6),
                new Card("spade-7", 7),
                new Card("spade-8", 8),
                new Card("spade-9", 9),
                new Card("spade-10", 10),
                new Card("spade-Q", 10),
                new Card("spade-J", 10),
                new Card("spade-K", 10)
        );
        cards.addAll(cards1);
    }

    public List<Card> cards = new ArrayList<>();


}
