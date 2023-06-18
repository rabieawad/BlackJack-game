package org.boes.praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {

    @Test
    void start() {
        Game.game.start();
        assertEquals(Game.game.CardsPlayer1.size(),2);
        assertEquals(Game.game.CardsPlayer2.size(),2);
        assertEquals(Game.game.Dealer.size(),2);

    }

    @Test
    void hit() {
        Game.game.start();

        Game.game.hit(1);
        assertEquals(3,Game.game.CardsPlayer1.size());
    }

    @Test
    void stand() {
        Game.game.start();

        Game.game.stand(1);
        assertTrue(Game.game.standPlayer1);

    }

    @Test
    void sumTotalPoints() {
        Game.game.start();
        int sum = Game.game.CardsPlayer1.get(0).value + Game.game.CardsPlayer1.get(1).value +100;
        Game.game.CardsPlayer1.add(new Card("testcard",100));
        assertEquals(sum,Game.game.sumTotalPoints(1));

    }

    @Test
    void finalStep() {
    }
}