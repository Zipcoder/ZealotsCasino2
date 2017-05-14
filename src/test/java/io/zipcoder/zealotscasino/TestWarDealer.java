package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andrewwong on 5/10/17.
 */
public class TestWarDealer {
    WarDealer dealer;
    Player player;

    @Before
    public void initWarDealer() {
        dealer = new WarDealer();
        player = new Player();
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
    public void evaluateCardValue_CardIsAKing_returnsThirteen() {
        //Given
        Card card = new Card(Card.CardValue.KING.name(), Card.Suit.HEARTS.name());
        int expected = 13;

        //When
        int actual = dealer.evaluateCardValue(card);

        //Then
        assertEquals("King should be 13", expected, actual);
    }

    @Test
    public void evaluateCardValue_CardIsAnAce_returnsFourteen() {
        //Given
        Card card = new Card(Card.CardValue.ACE.name(), Card.Suit.DIAMONDS.name());
        int expected = 14;

        //When
        int actual = dealer.evaluateCardValue(card);

        //Then
        assertEquals("ACE should be 14", expected, actual);
    }

    @Test
    public void evaluateCardValue_CardIsAFive_returnsFive() {
        //Given
        Card card = new Card(Card.CardValue.FIVE.name(), Card.Suit.SPADES.name());
        int expected = 5;

        //When
        int actual = dealer.evaluateCardValue(card);

        //Then
        assertEquals("ACE should be 14", expected, actual);
    }

    @Test
    public void processTieOutcome_PlayerWins_PlayerReceivesOriginalBet() {
        player.setWallet(100);
//        player.makeBet(20);
//        player.makeBet(20); //player doubles bet after tie
        double expected = 120;

        //When
        dealer.processTieOutcome("win", player);
        double actual = player.getWallet();

        //Then
        assertEquals("Wallet should now have $120 (100 - 20 + 40)", expected, actual, 0);
    }

    @Test
    public void processTieOutcome_PlayerLoses_WalletDecreasedByTwoBets() {
        player.setWallet(100);
//        player.makeBet(20);
//        player.makeBet(20); //player doubles bet after tie
        double expected = 60;

        //When
        dealer.processTieOutcome("lose", player);
        double actual = player.getWallet();

        //Then
        assertEquals("Wallet should now have $60", expected, actual, 0);
    }

    @Test
    public void processTieOutcome_PlayerTies_PlayerReceivesDoubleBet() {
        player.setWallet(100);
//        player.makeBet(20);
//        player.makeBet(20);
        double expected = 140;

        //When
        dealer.processTieOutcome("tie", player);
        double actual = player.getWallet();

        //Then
        assertEquals("Wallet should now have $140", expected, actual, 0);
    }


    // DONE
    @Test
    public void playRound_PlayersCardIsHigher_WinOutcomeReturned() {
        //Given
        String expectedOutcome = "win";
        Card playersCard = new Card(Card.CardValue.TEN.name(), Card.Suit.CLUBS.name());
        Card dealersCard = new Card(Card.CardValue.TWO.name(), Card.Suit.CLUBS.name());
        //When
        String actualOutcome = dealer.playRound(playersCard, dealersCard);
        //Then
        assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    public void playRound_DealersCardIsHigher_LoseOutcomeReturned() {
        //Given
        String expectedOutcome = "lose";
        Card playersCard = new Card(Card.CardValue.TEN.name(), Card.Suit.CLUBS.name());
        Card dealersCard = new Card(Card.CardValue.KING.name(), Card.Suit.SPADES.name());
        //When
        String actualOutcome = dealer.playRound(playersCard, dealersCard);
        //Then
        assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    public void playRound_CardsTie_TieOutcomeReturned() {
        //Given
        String expectedOutcome = "tie";
        Card playersCard = new Card(Card.CardValue.TEN.name(), Card.Suit.CLUBS.name());
        Card dealersCard = new Card(Card.CardValue.TEN.name(), Card.Suit.CLUBS.name());
        //When
        String actualOutcome = dealer.playRound(playersCard, dealersCard);
        //Then
        assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    public void processDeterminedOutcome_PlayerWins_WinningsAmountReturned() {
        //Given
        String outcome = "win";
        Bet bet = new Bet();
        bet.makeBet(20, player);
        dealer.setBet(bet);
        int expectedWinnings = 40;
        //When
        double actualWinnings = dealer.processDeterminedOutcome(outcome);
        //Then
        assertEquals(expectedWinnings, actualWinnings, .003);
    }

    @Test
    public void processDeterminedOutcome_PlayerLoses_ZeroAmountReturned() {
        //Given
        String outcome = "lose";
        Bet bet = new Bet();
        bet.makeBet(20, player);
        dealer.setBet(bet);
        int expectedWinnings = 0;
        //When
        double actualWinnings = dealer.processDeterminedOutcome(outcome);
        //Then
        assertEquals(expectedWinnings, actualWinnings, .003);
    }

    @Test
    public void processDeterminedOutcome_PlayerTies_NegativeOneAmountReturned() {
        //Given
        String outcome = "tie";
        Bet bet = new Bet();
        bet.makeBet(20, player);
        dealer.setBet(bet);
        int expectedWinnings = 20;
        //When
        double actualWinnings = dealer.processDeterminedOutcome(outcome);
        //Then
        assertEquals(expectedWinnings, actualWinnings, .003);
    }

    @Test
    public void determineOutcome_PlayerCardHigher_ReturnWin() {
        //Given
        int playersCardValue = 10;
        int dealersCardValue = 9;
        String expected = "win";

        //When
        String actual = dealer.determineOutcome(playersCardValue, dealersCardValue);

        //Then
        assertEquals("Player should have won", expected, actual);
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
        assertEquals("Player should have lost", expected, actual);
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
        assertEquals("The result should be a tie", expected, actual);
    }

}
