package io.zipcoder.zealotscasino;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by luisgarcia on 5/11/17.
 */
public class TestPokerDealer
{
        private PokerDealer dealer;
        private Player player;
        private Deck deck;
        private Hand hand;

        @Before
        public void setUp()
        {
                player = new Player();
                dealer = new PokerDealer();
                hand = new Hand();
                deck = new Deck();
        }


        @Test
        public void testDealHandTo_FiveCards()
        {
                //Given
                dealer.dealHand();
                int expected = 5;

                //When
                int actual = dealer.showPlayerHand().getCards().size();

                //Then
                assertEquals("Should deal out five cards", expected, actual);
        }

        @Test
        public void testSetPlayerHand()
        {
                //Given
                dealer.dealHand();
                dealer.setPlayerHand(hand);
                dealer.dealCard();
                int expected = 1; 

                //When
                int actual = dealer.showPlayerHand().getCards().size();

                //Then
                assertEquals("Should deal out five cards", expected, actual);
        }



        @Test
        public void checkStraight_TwoThroughSix_ReturnTrue()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("SIX","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("TWO","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("THREE","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("FOUR","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","CLUBS"));

                //when
                boolean actual = dealer.checkStraight();

                //then
                assertTrue("Should return true", actual);
        }

        @Test
        public void checkStraight_LowStraigt_ReturnTrue()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("ACE","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("TWO","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("THREE","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("FOUR","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","CLUBS"));

                //when
                boolean actual = dealer.checkStraight();

                //then
                assertTrue("Should return true", actual);
        }

        @Test
        public void checkFlush_AllDiamonds_ReturnTrue()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("SIX","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("TWO","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("FOUR","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","DIAMONDS"));

                //when
                boolean actual = dealer.checkFlush();

                //then
                assertTrue("All Diamonds should be flush", actual);
        }

        @Test
        public void checkFlush_AllDiamonds_Straight_ReturnTrue()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("SIX","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("TWO","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("THREE","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("FOUR","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","DIAMONDS"));

                //when
                boolean actual = dealer.checkStraightFlush();

                //then
                assertTrue("All Diamonds and 5 number in a row should return straight flush.", actual);
        }


        @Test
        public void checkRoyalFlush_AllDiamonds_Straight_ReturnTrue()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("TEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("JACK","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("QUEEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("ACE","DIAMONDS"));

                //when
                boolean actual = dealer.checkRoyalFlush();

                //then
                assertTrue("All Diamonds and 5 number in a row should return straight flush.", actual);
        }

        @Test
        public void testReturnNumberOfValuesInPlayerHand(){

                //Given
                dealer.getPlayerHand().receiveCard(new Card("TEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("JACK","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("JACK","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","SPADES"));
                int expected = 3;

                //When
                int actual = dealer.returnNumberOfValuesInPlayerHand();

                //Then
                assertEquals("There should be three different values", expected, actual);
        }


        @Test
        public void testCalculateHand_TestPair_ReturnsPair()
        {
                //Given
                boolean bool = false;
                dealer.getPlayerHand().receiveCard(new Card("FIVE","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("TEN","SPADES"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("TEN","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","SPADES"));
                String expected = "PAIR";

                //WHEN
                String actual = dealer.calculateHand();

                //THEN
                assertEquals("There should be a pair of Tens", expected, actual);
        }

        @Test
        public void evaluateThreeRanks_TwoPair_ReturnsTwoPair()
        {
                //Given
                boolean bool = false;
                dealer.getPlayerHand().receiveCard(new Card("FIVE","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","SPADES"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","SPADES"));
                String expected = "TWO PAIR";

                //WHEN
                String actual = dealer.evaluateThreeRanks();

                //THEN
                assertEquals("The result should be TWO PAIR", expected, actual);
        }

        @Test
        public void evaluateThreeRanks_ThreeFives_ReturnsThreeOfAKind()
        {
                //Given
                boolean bool = false;
                dealer.getPlayerHand().receiveCard(new Card("FIVE","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("FOUR","SPADES"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("FIVE","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","SPADES"));
                String expected = "THREE OF A KIND";

                //WHEN
                String actual = dealer.evaluateThreeRanks();

                //THEN
                assertEquals("The result should be THREE OF A KIND", expected, actual);
        }

        @Test
        public void evaluateTwoRanks_FourAces_ReturnsFourOfAKind()
        {
                //Given
                boolean bool = false;
                dealer.getPlayerHand().receiveCard(new Card("ACE","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("ACE","SPADES"));
                dealer.getPlayerHand().receiveCard(new Card("ACE","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("ACE","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","SPADES"));
                String expected = "FOUR OF A KIND";

                //WHEN
                String actual = dealer.evaluateTwoRanks();

                //THEN
                assertEquals("The result should be FOUR OF A KIND", expected, actual);
        }


        @Test
        public void evaluateTwoRanks_KingsAndSixes_ReturnsFullHouse()
        {
                //Given
                boolean bool = false;
                dealer.getPlayerHand().receiveCard(new Card("KING","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","SPADES"));
                dealer.getPlayerHand().receiveCard(new Card("KING","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("SIX","CLUBS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","SPADES"));
                String expected = "FULL HOUSE";

                //WHEN
                String actual = dealer.evaluateTwoRanks();

                //THEN
                assertEquals("The result should be FULL HOUSE", expected, actual);
        }

        @Test
        public void testPayPlayer_NoPair_PayOut()
        {
                //given
                player.setWallet(50);
                dealer.getBet().makeBet(20, player);
                double expected = 30;

                //when
                dealer.payPlayer(player, "NO PAIR");
                double actual = player.getWallet();

                //then
                assertEquals("Testing no pair loss", expected, actual,0);
        }

        @Test
        public void testPayPlayer_RoyalFlush_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 19550;

                //when
                dealer.payPlayer(player, "ROYAL FLUSH");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_StraightFlush_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 1030;

                //when
                dealer.payPlayer(player, "STRAIGHT FLUSH");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_FourOfAKind_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 530;

                //when
                dealer.payPlayer(player, "FOUR OF A KIND");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_FullHouse_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 210;

                //when
                dealer.payPlayer(player, "FULL HOUSE");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_Flush_PayOut()
        {
                //given

                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expeted = 150;

                //when
                dealer.payPlayer(player, "FLUSH");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expeted, actual,0);
        }

        @Test
        public void testPayPlayer_Straight_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 110;

                //when
                dealer.payPlayer(player, "STRAIGHT");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_ThreeOfAKind_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 90;

                //when
                dealer.payPlayer(player, "THREE OF A KIND");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_TwoPair_PayOut()
        {
                //given
                player.setWallet(50);

                dealer.getBet().makeBet(20, player); 

                double expected = 70;

                //when
                dealer.payPlayer(player, "TWO PAIR");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expected, actual,0);
        }

        @Test
        public void testPayPlayer_Pair()
        {
                //given

                player.setWallet(50);

                dealer.getBet().makeBet(20, player);

                double expeted = 50;

                //when
                dealer.payPlayer(player, "PAIR");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expeted, actual,0);
        }

        @Test
        public void testEvaluateFiveRanks_RoyalFlush()
        {
                //given
                Card[] cards = { new Card("TEN","DIAMONDS"),
                                 new Card("JACK","DIAMONDS"),
                                 new Card("QUEEN","DIAMONDS"),
                                 new Card("KING","DIAMONDS"),
                                 new Card("ACE","DIAMONDS") };
                ArrayList<Card> cardList = new ArrayList<>();
                for (Card card : cards) cardList.add(card);
                String expected = "ROYAL FLUSH";
                Hand hand = new Hand();
                dealer.getPlayerHand().receiveCards(cardList);

                //when
                String actual = dealer.evaluateFiveRanks();

                assertEquals("Royal Flush", expected, actual);

        }

        @Test
        public void testEvaluateFiveRanks_StraightFlush()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("TEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("JACK","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("QUEEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("NINE","DIAMONDS"));
                String expected = "STRAIGHT FLUSH";

                //when
                String actual = dealer.evaluateFiveRanks();

                assertEquals("straight flush", expected, actual);
        }

        @Test
        public void testEvaluateFiveRanks_Flush()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("TEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("JACK","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("TWO","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("ACE","DIAMONDS"));
                String expected = "FLUSH";

                //when
                String actual = dealer.evaluateFiveRanks();

                assertEquals("Flush", expected, actual);

        }

        @Test
        public void testEvaluateFiveRanks_Straight()
        {
                //given
                dealer.getPlayerHand().receiveCard(new Card("TEN","HEARTS"));
                dealer.getPlayerHand().receiveCard(new Card("JACK","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("QUEEN","DIAMONDS"));
                dealer.getPlayerHand().receiveCard(new Card("KING","SPADES"));
                dealer.getPlayerHand().receiveCard(new Card("NINE","DIAMONDS"));
                String expected = "STRAIGHT";

                //when
                String actual = dealer.evaluateFiveRanks();

                assertEquals("straight", expected, actual);

        }
        


}
