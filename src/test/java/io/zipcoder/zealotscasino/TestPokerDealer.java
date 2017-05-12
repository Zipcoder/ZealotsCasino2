package io.zipcoder.zealotscasino;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by luisgarcia on 5/11/17.
 */
public class TestPokerDealer
{
        private PokerDealer dealer;
        private Player player;


        @Before
        public void setUp()
        {
                player = new Player();
                dealer = new PokerDealer();
        }

        @Test
        public void checkStraight_TwoThroughSix_ReturnTrue()
        {
                //given
                player.getHand().receiveCard(new Card("SIX","CLUBS"));
                player.getHand().receiveCard(new Card("TWO","CLUBS"));
                player.getHand().receiveCard(new Card("THREE","CLUBS"));
                player.getHand().receiveCard(new Card("FOUR","CLUBS"));
                player.getHand().receiveCard(new Card("FIVE","CLUBS"));

                //when
                boolean actual = dealer.checkStraight(player);

                //then
                assertTrue("Should return true", actual);
        }

        @Test
        public void checkStraight_LowStraigt_ReturnTrue()
        {
                //given
                player.getHand().receiveCard(new Card("ACE","CLUBS"));
                player.getHand().receiveCard(new Card("TWO","CLUBS"));
                player.getHand().receiveCard(new Card("THREE","CLUBS"));
                player.getHand().receiveCard(new Card("FOUR","CLUBS"));
                player.getHand().receiveCard(new Card("FIVE","CLUBS"));

                //when
                boolean actual = dealer.checkStraight(player);

                //then
                assertTrue("Should return true", actual);
        }

        @Test
        public void checkFlush_AllDiamonds_ReturnTrue()
        {
                //given
                player.getHand().receiveCard(new Card("SIX","DIAMONDS"));
                player.getHand().receiveCard(new Card("TWO","DIAMONDS"));
                player.getHand().receiveCard(new Card("KING","DIAMONDS"));
                player.getHand().receiveCard(new Card("FOUR","DIAMONDS"));
                player.getHand().receiveCard(new Card("FIVE","DIAMONDS"));

                //when
                boolean actual = dealer.checkFlush(player);

                //then
                assertTrue("All Diamonds should be flush", actual);
        }

        @Test
        public void checkFlush_AllDiamonds_Straight_ReturnTrue()
        {
                //given
                player.getHand().receiveCard(new Card("SIX","DIAMONDS"));
                player.getHand().receiveCard(new Card("TWO","DIAMONDS"));
                player.getHand().receiveCard(new Card("THREE","DIAMONDS"));
                player.getHand().receiveCard(new Card("FOUR","DIAMONDS"));
                player.getHand().receiveCard(new Card("FIVE","DIAMONDS"));

                //when
                boolean actual = dealer.checkStraightFlush(player);

                //then
                assertTrue("All Diamonds and 5 number in a row should return straight flush.", actual);
        }


        @Test
        public void checkRoyalFlush_AllDiamonds_Straight_ReturnTrue()
        {
                //given
                player.getHand().receiveCard(new Card("TEN","DIAMONDS"));
                player.getHand().receiveCard(new Card("JACK","DIAMONDS"));
                player.getHand().receiveCard(new Card("QUEEN","DIAMONDS"));
                player.getHand().receiveCard(new Card("KING","DIAMONDS"));
                player.getHand().receiveCard(new Card("ACE","DIAMONDS"));

                //when
                boolean actual = dealer.checkRoyalFlush(player);

                //then
                assertTrue("All Diamonds and 5 number in a row should return straight flush.", actual);
        }

        @Test
        public void testReturnNumberOfValuesInPlayerHand(){

                //Given
                player.getHand().receiveCard(new Card("TEN","DIAMONDS"));
                player.getHand().receiveCard(new Card("JACK","DIAMONDS"));
                player.getHand().receiveCard(new Card("JACK","HEARTS"));
                player.getHand().receiveCard(new Card("KING","DIAMONDS"));
                player.getHand().receiveCard(new Card("KING","SPADES"));
                int expected = 3;

                //When
                int actual = dealer.returnNumberOfValuesInPlayerHand(player);

                //Then
                assertEquals("There should be three different values", expected, actual);
        }


        @Test
        public void testCalculateHand_TestPair_ReturnsPair()
        {
                //Given
                boolean bool = false;
                player.getHand().receiveCard(new Card("FIVE","DIAMONDS"));
                player.getHand().receiveCard(new Card("TEN","SPADES"));
                player.getHand().receiveCard(new Card("SIX","HEARTS"));
                player.getHand().receiveCard(new Card("TEN","CLUBS"));
                player.getHand().receiveCard(new Card("KING","SPADES"));
                String expected = "PAIR";

                //WHEN
                String actual = dealer.calculateHand(player);

                //THEN
                assertEquals("There should be a pair of Tens", expected, actual);
        }

        @Test
        public void evaluateThreeRanks_TwoPair_ReturnsTwoPair()
        {
                //Given
                boolean bool = false;
                player.getHand().receiveCard(new Card("FIVE","DIAMONDS"));
                player.getHand().receiveCard(new Card("FIVE","SPADES"));
                player.getHand().receiveCard(new Card("SIX","HEARTS"));
                player.getHand().receiveCard(new Card("SIX","CLUBS"));
                player.getHand().receiveCard(new Card("KING","SPADES"));
                String expected = "TWO PAIR";

                //WHEN
                String actual = dealer.evaluateThreeRanks(player);

                //THEN
                assertEquals("The result should be TWO PAIR", expected, actual);
        }

        @Test
        public void evaluateThreeRanks_ThreeFives_ReturnsThreeOfAKind()
        {
                //Given
                boolean bool = false;
                player.getHand().receiveCard(new Card("FIVE","DIAMONDS"));
                player.getHand().receiveCard(new Card("FOUR","SPADES"));
                player.getHand().receiveCard(new Card("FIVE","HEARTS"));
                player.getHand().receiveCard(new Card("FIVE","CLUBS"));
                player.getHand().receiveCard(new Card("SIX","SPADES"));
                String expected = "THREE OF A KIND";

                //WHEN
                String actual = dealer.evaluateThreeRanks(player);

                //THEN
                assertEquals("The result should be THREE OF A KIND", expected, actual);
        }

        @Test
        public void evaluateTwoRanks_FourAces_ReturnsFourOfAKind()
        {
                //Given
                boolean bool = false;
                player.getHand().receiveCard(new Card("ACE","DIAMONDS"));
                player.getHand().receiveCard(new Card("ACE","SPADES"));
                player.getHand().receiveCard(new Card("ACE","HEARTS"));
                player.getHand().receiveCard(new Card("ACE","CLUBS"));
                player.getHand().receiveCard(new Card("SIX","SPADES"));
                String expected = "FOUR OF A KIND";

                //WHEN
                String actual = dealer.evaluateTwoRanks(player);

                //THEN
                assertEquals("The result should be FOUR OF A KIND", expected, actual);
        }


        @Test
        public void evaluateTwoRanks_KingsAndSixes_ReturnsFullHouse()
        {
                //Given
                boolean bool = false;
                player.getHand().receiveCard(new Card("KING","DIAMONDS"));
                player.getHand().receiveCard(new Card("SIX","SPADES"));
                player.getHand().receiveCard(new Card("KING","HEARTS"));
                player.getHand().receiveCard(new Card("SIX","CLUBS"));
                player.getHand().receiveCard(new Card("KING","SPADES"));
                String expected = "FULL HOUSE";

                //WHEN
                String actual = dealer.evaluateTwoRanks(player);

                //THEN
                assertEquals("The result should be FULL HOUSE", expected, actual);
        }

        @Test
        public void testPayPlayer_Flush_Win_130()
        {
                //given

                player.setWallet(50);
                player.makeBet(20);
                double expeted = 150;

                //when
                dealer.payPlayer(player, "FLUSH");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expeted, actual,0);
        }

        @Test
        public void testPayPlayer_RoyalFlush()
        {
                //given
                player.setWallet(50);
                player.makeBet(20);
                double expeted = 19550;

                //when
                dealer.payPlayer(player, "ROYAL FLUSH");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expeted, actual,0);
        }

        @Test
        public void testPayPlayer()
        {
                //given

                player.setWallet(50);
                player.makeBet(20);
                double expeted = 50;

                //when
                dealer.payPlayer(player, "PAIR");
                double actual = player.getWallet();

                //then
                assertEquals("Testing flush payout", expeted, actual,0);
        }




}
