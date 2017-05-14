package io.zipcoder.zealotscasino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by denniskalaygian on 5/10/17.
 */
public class TestBlackJackDealer {
    private Player player;
    private BlackJackDealer blackJackDealer;
    private Deck deck;

    @Before
    public void setUp() {
        player = new Player();
        blackJackDealer = new BlackJackDealer();
        deck = new Deck();
    }

    //dealCardToPlayer
    @Test
    public void testDealCardToPlayer_PlayerHasNoCards_PlayerIsDealtACard(){
        //Given
        int predictedSizeOfHand = 1;
        //When
        blackJackDealer.dealCardToPlayer();
        //Then
        Assert.assertEquals("Checking to see if player received a card", predictedSizeOfHand, blackJackDealer.getPlayerHand().getCards().size());
    }

    //dealCardToDealer
    @Test
    public void dealCardToDealer_DealerHasNoCards_DealerIsDealtACard(){
        //Given
        int predictedSizeOfHand = 1;
        //When
        blackJackDealer.dealCardToDealer();
        //Then
        Assert.assertEquals("Checking to see if player received a card", predictedSizeOfHand, blackJackDealer.getDealerHand().getCards().size());
    }

    //buildPlayerHand
    @Test
    public void testBuildPlayerHand_PlayerHasNoHand_PlayerReceivesTwoCards(){
        //Given
        int expectedSize = 2;
        //When
        blackJackDealer.buildPlayerHand();
        //Then
        Assert.assertEquals("Checking to see if Player received two cards", expectedSize, blackJackDealer.getPlayerHand().getCards().size());
    }

    @Test
    public void testBuildPlayerHand_PlayerHasNoHand_PlayerReceivesArrayListOfCards(){
        //Given
        Class expectedClass = ArrayList.class;
        //When
        blackJackDealer.buildPlayerHand();
        //Then
        Assert.assertEquals("Checking to see if Player received two cards", expectedClass, blackJackDealer.getPlayerHand().getCards().getClass());
    }

    //buildDealerHand
    @Test
    public void testBuildDealerHand_DealerHasNoHand_DealerReceivesTwoCards(){
        //Given
        int expectedSize = 2;
        //When
        blackJackDealer.buildDealerHand();
        //Then
        Assert.assertEquals("Checking to see if Dealer received two cards", expectedSize, blackJackDealer.getDealerHand().getCards().size());
    }

    @Test
    public void testBuildDealerHand_DealerHasNoHand_DealerReceivesArrayListOfCards(){
        //Given
        Class expectedClass = ArrayList.class;
        //When
        blackJackDealer.buildDealerHand();
        //Then
        Assert.assertEquals("Checking to see if Player received two cards", expectedClass, blackJackDealer.getPlayerHand().getCards().getClass());
    }

    //initializeHands
    @Test
    public void testInitializeHands_PlayerHasAHand_PlayersHandValueIsReset(){
        //Given
        int expectedValue = 0;
        blackJackDealer.dealCardToPlayer();
        //When
        blackJackDealer.initializeHands();
        //Then
        Assert.assertEquals("Checking to see if player hand value is zero", expectedValue, blackJackDealer.getPlayerHand().getHandValue());
    }

    @Test
    public void testInitializeHands_DealerHasAHand_DealersHandValueIsReset(){
        //Given
        int expectedValue = 0;
        blackJackDealer.dealCardToDealer();
        //When
        blackJackDealer.initializeHands();
        //Then
        Assert.assertEquals("Checking to see if player hand value is zero", expectedValue, blackJackDealer.getDealerHand().getHandValue());
    }


    //dealHandToPlayer
    @Test
    public void testDealHandToPlayer_PlayerHasNoHand_PlayerHasTwoCards(){
        //Given
        int expectedCardAmount = 2;
        //When
        blackJackDealer.dealHandToPlayer();
        //Then
        Assert.assertEquals("Checking to see if the player receives two cards", expectedCardAmount, blackJackDealer.getPlayerHand().getCards().size());
    }

    @Test
    public void testDealHandToPlayer_PlayerHasNoHand_PlayerHasArrayOfTwoCards(){
        //Given
        Class expectedClass = ArrayList.class;
        //When
        blackJackDealer.dealHandToPlayer();
        //Then
        Assert.assertEquals("Checking to see if the player receives an array of two cards", expectedClass, blackJackDealer.getPlayerHand().getCards().getClass());
    }

    @Test
    public void testDealHandToPlayer_PlayerHasNoHand_PlayersHandValueUpdates(){
        //When
        blackJackDealer.dealHandToPlayer();
        //Then
        Assert.assertTrue("Checking to see if the players hand value updates", blackJackDealer.getPlayerHand().getHandValue() > 0);
    }

    //dealHandToDealer
    @Test
    public void testDealHandToDealer_DealerHasNoHand_DealerHasTwoCards(){
        //Given
        int expectedCardAmount = 2;
        //When
        blackJackDealer.dealHandToDealer();
        //Then
        Assert.assertEquals("Checking to see if the dealer receives two cards", expectedCardAmount, blackJackDealer.getDealerHand().getCards().size());
    }

    @Test
    public void testDealHandToDealer_DealerHasNoHand_DealerHasArrayOfTwoCards(){
        //Given
        Class expectedClass = ArrayList.class;
        //When
        blackJackDealer.dealCardToDealer();
        //Then
        Assert.assertEquals("Checking to see if the dealer receives an array of two cards", expectedClass, blackJackDealer.getDealerHand().getCards().getClass());
    }

    @Test
    public void testDealHandToDealer_DealerHasNoHand_DealersHandValueUpdates(){
        //When
        blackJackDealer.dealCardToDealer();
        //Then
        Assert.assertTrue("Checking to see if the dealers hand value updates", blackJackDealer.getDealerHand().getHandValue() > 0);
    }

    //pay
    @Test
    public void testPay_PlayerWins_PlayerIsPaid(){
        //Given
        int expectedAmount = 220;
        player.setWallet(200);
        //When
        blackJackDealer.pay(player, 20);
        //Then
        Assert.assertEquals("Checking to see if the player is paid the proper amount", expectedAmount, player.getWallet(), 0);
    }

    //takeHit
    @Test
    public void testTakeHit_PlayerTakesAHit_PlayerReceivesACard(){
        //Given
        blackJackDealer.dealHandToPlayer();
        int expectedCardCount = 3;
        //When
        blackJackDealer.takeHit();
        //Then
        Assert.assertEquals("Checking to see if the player received a card", expectedCardCount, blackJackDealer.getPlayerHand().getCards().size());
    }

    @Test
    public void testTakeHit_PlayerTakesAHit_PlayerHandValueIncreases(){
        //When
        blackJackDealer.takeHit();
        //Then
        Assert.assertTrue("Checking to see if the players hand value increased", blackJackDealer.getPlayerHand().getHandValue() > 0);
    }

    //checkIfDealerHit
    @Test
    public void testCheckIfDealerHit_DealerTakesAHit_DealerReceivesACard(){
        //Given
        blackJackDealer.dealCardToDealer();
        int expectedCardCount = 2;
        //When
        blackJackDealer.checkIfDealerHit();
        //Then
        Assert.assertEquals("Checking to see if the player received a card", expectedCardCount, blackJackDealer.getDealerHand().getCards().size());
    }

    @Test
    public void testCheckIfDealerHit_DealerTakesAHit_DealerHandValueIncreases(){
        //When
        blackJackDealer.checkIfDealerHit();
        //Then
        Assert.assertTrue("Checking to see if the players hand value increased", blackJackDealer.getDealerHand().getHandValue() > 0);
    }

    //checkStatus
    @Test
    public void testCheckStatus_PlayerBusts_ReturnsFalse(){
        //Given
        blackJackDealer.getPlayerHand().setHandValue(22);
        //When
        boolean returnValue = blackJackDealer.checkStatus(player, 20);
        //Then
        Assert.assertTrue("Checking to see if the method returns false", !returnValue);
    }

    @Test
    public void testCheckStatus_PlayerHasBlackJack_ReturnsFalse(){
        //Given
        blackJackDealer.getPlayerHand().setHandValue(21);
        //When
        boolean returnValue = blackJackDealer.checkStatus(player, 20);
        //Then
        Assert.assertTrue("Checking to see if the method returns false", !returnValue);
    }

    @Test
    public void testCheckStatus_PlayerHasBlackJack_PlayerIsPaid(){
        //Given
        blackJackDealer.getPlayerHand().setHandValue(21);
        player.setWallet(100);
        double expectedValue = 140;
        //When
        boolean returnValue = blackJackDealer.checkStatus(player, 20);
        //Then
        Assert.assertEquals("Checking to see if the method pays the player", expectedValue, player.getWallet(), 0);
    }

    //assertBlackJack
    @Test
    public void testAssertBlackJack_PlayerHasBlackJack_GameIsNoLongerRunning(){
        //Given
        blackJackDealer.getPlayerHand().setHandValue(21);
        blackJackDealer.setGameRunning(true);
        //When
        blackJackDealer.assertBlackJack(player);
        //Then
        Assert.assertTrue("Checking to see if method registered blackjack", !blackJackDealer.isGameRunning());
    }

    @Test
    public void testAssertBlackJack_PlayerDoesntHaveBlackJack_GameIsStillRunning(){
        //Given
        blackJackDealer.getPlayerHand().setHandValue(20);
        blackJackDealer.setGameRunning(true);
        //When
        blackJackDealer.assertBlackJack(player);
        //Then
        Assert.assertTrue("Checking to see if method registered blackjack", blackJackDealer.isGameRunning());
    }

    @Test
    public void testAssertBlackJack_PlayerBusts_PlayerDoesntReceiveBlackJack(){
        //Given
        blackJackDealer.getPlayerHand().setHandValue(22);
        blackJackDealer.setGameRunning(true);
        //When
        blackJackDealer.assertBlackJack(player);
        //Then
        Assert.assertTrue("Checking to see if method registered blackjack", blackJackDealer.isGameRunning());
    }

    //payPlayer
    @Test
    public void testPayPlayer(){}
    
    //evaluateResult
    @Test
    public void testEvaluateResult(){}



}
