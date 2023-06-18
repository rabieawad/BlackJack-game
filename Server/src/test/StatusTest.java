package org.boes.praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void init() {
        Game.game.coinsPlayer1 = 10;
        Game.game.coinsPlayer2 = 20;
        Game.game.CardsPlayer2.add(new Card("testcards", 200));
        Game.game.CardsPlayer1.add(new Card("testcards", 21));
        Game.game.Dealer.add(new Card("testcards", 2));
        Status st = new Status();
        assertEquals(st.p1sum,21);
        assertEquals(st.p2sum,200);
        assertTrue(st.p1won);
        assertTrue(st.p2lost);
        assertFalse(st.p2won);
        assertFalse(st.p1lost);
     }
}