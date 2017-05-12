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
        public void testCheckFullHouse()
        {
                //Given
                player.getHand().receiveCard(new Card("TEN","DIAMONDS"));
                player.getHand().receiveCard(new Card("TEN","SPADES"));
                player.getHand().receiveCard(new Card("TEN","HEARTS"));
                player.getHand().receiveCard(new Card("KING","DIAMONDS"));
                player.getHand().receiveCard(new Card("KING","SPADES"));

                //WHEN
                boolean myBool = dealer.checkFullHouse(player);

                //THEN
                assertTrue("Should return is full house", myBool);
        }

        @Test
        public void testFourOfAKind()
        {
                //Given
                boolean bool = false;
                player.getHand().receiveCard(new Card("TEN","DIAMONDS"));
                player.getHand().receiveCard(new Card("TEN","SPADES"));
                player.getHand().receiveCard(new Card("TEN","HEARTS"));
                player.getHand().receiveCard(new Card("TEN","CLUBS"));
                player.getHand().receiveCard(new Card("KING","SPADES"));

                if(!dealer.checkFullHouse(player)) //given that we check two different values in hand
                {
                        bool = true;
                }


                //WHEN
                boolean myBool = dealer.checkFourOfAKind(bool);

                //THEN
                assertTrue("Should return is fouur of a kind", myBool);
        }

}
