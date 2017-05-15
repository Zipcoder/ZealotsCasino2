package io.zipcoder.zealotscasino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by aaronlong on 5/14/17.
 */
public class TestGame {

    private Game game;

    @Before
    public void setUp() {
        game = new Game(new Player());
    }

    @Test
    public void testMakeDealer_IntegerValOne_WarDealer() {
        Dealer dealer = game.makeDealer(1);
        Assert.assertTrue("Confirm WarDealer creation", dealer instanceof WarDealer);
    }

    @Test
    public void testMakeDealer_IntegerValTwo_BlackJackDealer() {
        Dealer dealer = game.makeDealer(2);
        Assert.assertTrue("Confirm BlackJackDealer creation", dealer instanceof BlackJackDealer);
    }

    @Test
    public void testMakeDealer_IntegerValThree_PokerDealer() {
        Dealer dealer = game.makeDealer(3);
        Assert.assertTrue("Confirm PokerDealer creation", dealer instanceof PokerDealer);
    }

    @Test
    public void testMakeDealer_IntegerValFour_WarDealer() {
        Dealer dealer = game.makeDealer(4);
        Assert.assertTrue("Confirm BigSixDealer creation", dealer instanceof BigSixDealer);
    }

    @Test
    public void testMakeDealer_IntegerValSeventeen_Null() {
        Dealer dealer = game.makeDealer(17);
        Assert.assertNull("Confirm BigSixDealer creation", dealer);
    }


}
