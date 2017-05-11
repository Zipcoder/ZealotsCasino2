package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andrewwong on 5/10/17.
 */
public class TestWarDealer {
    WarDealer dealer;
    Player player;

    @Before
    public void initWarDealer(){
        dealer = new WarDealer();
        player = new Player();
    }
    @Test
    public void dealHandTo_DealerHasCard_PlayerReceivesCard(){
        //Given
        Player player = new Player();
        int expectedSizeOfHand = 1;

        //When
        dealer.dealHandTo(player);

        //Then
        assertEquals("Player did not receive card", expectedSizeOfHand, player.getHand().getCards().size());
    }

    @Test
    public void pay_PlayerHasWallet_PlayerReceivesPayout() {
        //Given
        player.setWallet(0);
        double expectedWallet = 100;

        //When
        dealer.pay(player, 100);
        double actualWallet = player.getWallet();

        //Then
        assertEquals("Player did not receive payout", expectedWallet, actualWallet, 0D);
    }

    @Test
    public void testEvaluateCardValue_CardIsAKing_returnsThirteen(){
        //Given
        Card card = new Card(Card.CardValue.KING.name(), Card.Suit.HEARTS.name());
        int expected = 13;

        //When
        int actual = dealer.evaluateCardValue(card);

        //Then
        assertEquals("King should be 13", expected, actual);
    }

    @Test
    public void determineOutcome_PlayerCardHigher_ReturnWin(){
        //Given
        int playersCardValue = 10;
        int dealersCardValue = 9;
        String expected = "win";

        //When
        String actual = dealer.determineOutcome(playersCardValue, dealersCardValue);

        //Then
        assertEquals("Player should have won",expected, actual);
    }

    @Test
    public void determineOutcome_PlayerCardLower_ReturnsLose() {

        //: Given
        int playersCardValue = 9;
        int dealersCardValue = 10;
        String expected = "lose";

        //: When
        String actual = dealer.determineOutcome(playersCardValue, dealersCardValue);

        //: Then
        assertEquals("Player should have lost",expected, actual);
    }

    @Test
    public void determineOutcome_EqualValue_ReturnsTie() {

        //: Given
        int playersCardValue = 10;
        int dealersCardValue = 10;
        String expected = "tie";

        //: When
        String actual = dealer.determineOutcome(playersCardValue, dealersCardValue);

        //: Then
        assertEquals("The result should be a tie",expected, actual);
    }

//    @Test
//    public void processDeterminedOutcome_PlayerWins_PlayerReceivesTwiceBet(){
//        //Given
//        player.setWallet(100);
//        player.makeBet(5);
//        double expected = 105;
//
//        //When
//        dealer.processDeterminedOutcome("win", player);
//        double actual = player.getWallet();
//
//        //Then
//        assertEquals("Wallet should now have $105 (100 - 5 + 10)", expected, actual, 0);
//    }
//
//    @Test
//    public void processTieRound_PlayerWins_PlayerReceivesOriginalBet(){
//        player.setWallet(100);
//        player.makeBet(5);
//        player.makeBet(5);
//        double expected = 105;
//
//        //When
//        dealer.processTieRound("win", player);
//        double actual = player.getWallet();
//
//        //Then
//        assertEquals("Wallet should now have $105 (100 - 5 + 10)", expected, actual, 0);
//    }

//    @Test
//    public void processTieRound_PlayerLoses_WalletDecreasedByTwoBets(){
//        player.setWallet(100);
//        player.makeBet(5);
//        player.makeBet(5);
//        double expected = 90;
//
//        //When
//        dealer.processTieRound("lose", player);
//        double actual = player.getWallet();
//
//        //Then
//        assertEquals("Wallet should now have $105 (100 - 5 + 10)", expected, actual, 0);
//    }

//    @Test
//    public void processTieRound_PlayerTies_PlayerReceivesDoubleBet(){
//        player.setWallet(100);
//        player.makeBet(5);
//        player.makeBet(5);
//        double expected = 110;
//
//        //When
//        dealer.processTieRound("tie", player);
//        double actual = player.getWallet();
//
//        //Then
//        assertEquals("Wallet should now have $105 (100 - 5 + 10)", expected, actual, 0);
//    }
}
