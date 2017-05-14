package io.zipcoder.zealotscasino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void testBuildDealerHand(){}

    //initializeHands
    @Test
    public void testInitializeHands(){}

    //dealHandToPlayer
    @Test
    public void testDealHandToPlayer(){}

    //dealHandToDealer
    @Test
    public void testDealHandToDealer(){}

    //pay
    @Test
    public void testPay(){}

    //takeHit
    @Test
    public void testTakeHit(){}

    //checkIfPlayerHit
    @Test
    public void testCheckIfPlayerHit(){}

    //decideWinner
    @Test
    public void testDecideWinner(){}

    //checkStatus
    @Test
    public void testCheckStatus(){}

    //assertBlackJack
    @Test
    public void testAssertBlackJack(){}

    //hitProcess
    @Test
    public void testHitProcess(){}

    //evaluateResult
    @Test
    public void testEvaluateResult(){}

    //checkIfDealerHit
    @Test
    public void testCheckIfDealerHit(){}

    //payPlayer
    @Test
    public void testPayPlayer(){}

}
