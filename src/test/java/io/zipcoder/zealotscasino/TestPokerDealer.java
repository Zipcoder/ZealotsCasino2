package io.zipcoder.zealotscasino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionListener;

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
                Assert.assertTrue("Should return true", actual);
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
                Assert.assertTrue("All Diamonds should be flush", actual);
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
                boolean actual = dealer.checkStraightlFlush(player);

                //then
                Assert.assertTrue("All Diamonds and 4 number in a row should return straight flush.", actual);
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
                boolean actual = dealer.checkRoayalFlush(player);

                //then
                Assert.assertTrue("All Diamonds and 4 number in a row should return straight flush.", actual);
        }

}
