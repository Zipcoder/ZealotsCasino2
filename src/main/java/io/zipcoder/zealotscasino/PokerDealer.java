package io.zipcoder.zealotscasino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by luisgarcia on 5/11/17.
 */
public class PokerDealer implements CardDealer
{
    private Deck deck;

    public PokerDealer()
    {
        deck = new Deck();
        deck.buildDeck();
    }


    public void dealCardTo(Player player)
    {
        player.getHand().receiveCard(deck.surrenderCard());
    }


    public void dealHandTo(Player player)
    {
        for (int i = 0; i < 5; i++)
        {
            dealCardTo(player);
        }
    }


    public void pay(Player player, double payOut)
    {
        player.collectWinnings(payOut);
    }


    public void play(Player player)
    {
        //get bet
        makeBet(player);

        //deal cards to player
        dealHandTo(player);

        //display
        userDisplayHand(player);

        //discard cards
        int numberToReplace = discardCards(player);

        //replace discarded cards
        replace(player, numberToReplace);

        //display new hand
        System.out.println("Updated Hand: ");
        userDisplayHand(player);

        //calculate hand
       // String handRank = calculateHand(player);

        // determine winnings and pay

    }

    public void calculateHand(Player player)
    {
       // int numberOfValues = determineNumberOfValues(player);
    }

    public int returnNumberOfValuesInPlayerHand(Player player)
    {
        ArrayList<Card> playerHand = player.getHand().getCards();
        Map<Integer, Integer> mapOfValues = new HashMap<>();


        for(int i = 0; i < playerHand.size(); i++){

            Integer key = playerHand.get(i).getValue();
            Integer frequency = mapOfValues.get(key);

            mapOfValues.put(key, frequency == null? 1 : frequency + 1);
        }

        Integer amountOfKey = mapOfValues.keySet().size();
        return  amountOfKey;
    }

    public boolean checkRoyalFlush(Player player)
    {
        ArrayList<Card> playerHand = player.getHand().getCards();
        Collections.sort(playerHand);

        if(returnNumberOfValuesInPlayerHand(player) == 5 && (playerHand.get(0).getValue() == 10))
        {
            return true;
        }
        return false;
    }

    public boolean checkFullHouse(Player player)
    {


        if(returnNumberOfValuesInPlayerHand(player) == 2)
        {
            ArrayList<Card> playerHand = player.getHand().getCards();
            Collections.sort(playerHand);

            boolean myBoolean = playerHand.get(0).getValue() == playerHand.get(1).getValue();
            boolean myBoolean2 = playerHand.get(3).getValue() == playerHand.get(4).getValue();

            if(myBoolean && myBoolean2)
            {
                return true;
            }
            else
                checkFourOfAKind(true);
        }
        return false;
    }

    public boolean checkFourOfAKind(boolean mybool)
    {
        return mybool;
    }

    public boolean checkStraight(Player player)
    {
        ArrayList<Card> playerHand = player.getHand().getCards();
        Collections.sort(playerHand);
        int value = playerHand.get(0).getValue();

        for(int i = 1; i < 4; i++)
        {
            if((playerHand.get(i).getValue() == value+1) && (playerHand.get(4).getFaceValue().equals("ACE")))
            {
                value++;
                if(value == 5)
                {
                    return true;
                }
            }
        }


        for(int i = 1; i < 5; i++)
        {
            if(playerHand.get(i).getValue() == value + 1)
            {
                value++;
            } else
                return false;
        }
        return true;
    }

    public boolean checkStraightFlush(Player player)
    {

        if(checkFlush(player) && checkStraight(player))
        {
            return true;
        }
        return false;
    }

    public boolean checkFlush(Player player)
    {
        ArrayList<Card> playerHand = player.getHand().getCards();
        String myString = playerHand.get(0).getSuit();

        for(int i = 1; i < 5; i++)
        {
            if(!playerHand.get(i).getSuit().equals(myString))
            {
                return false;
            }

        }
        return true;
    }

    public void replace(Player player, int numberToReplace)
    {
        for (int i = 0; i < numberToReplace; i++)
        {
            dealCardTo(player);
        }
    }


    public void makeBet(Player player)
    {
        try
        {
            player.makeBet(getDoubleInput("Make a bet"));
        } catch (IllegalArgumentException e)
        {
            System.out.println("Insufficient Funds.");
            play(player);
            return;
        } catch (SecurityException e)
        {
            System.out.println("Minimum bet is $20.");
            play(player);
            return;
        }
    }


    public int discardCards(Player player)
    {
        double numCardstoDiscard = getDoubleInput("How many cards do you want to discard? ");
        for(int i = 0; i < numCardstoDiscard; i++)
        {
            double getDiscard = getDoubleInput("Please enter the index of the card that is to be discarded: ");
            player.getHand().remove((int)getDiscard - 1);
        }
        userDisplayHand(player);
        return (int)numCardstoDiscard;
    }

    public void userDisplayHand(Player player)
    {
        StringBuilder outPut = new StringBuilder(1000);
        ArrayList<Card> cards = player.getHand().getCards();
        for (int i = 0; i < cards.size(); i++)
        {
            outPut.append("[" + (i + 1) + "]: " + cards.get(i) + "\n");
        }
        System.out.println(outPut);
    }
}
