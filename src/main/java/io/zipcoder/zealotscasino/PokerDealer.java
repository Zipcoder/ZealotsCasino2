package io.zipcoder.zealotscasino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getIntInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by luisgarcia on 5/11/17.
 */
public class PokerDealer implements Dealer
{
    private Deck deck;
    private Hand playerHand;
    private Bet bet;

    public PokerDealer()
    {
        bet = new Bet();
        deck = new Deck();
        playerHand = new Hand();
        deck.buildDeck();
    }

    @Override
    public Bet getBet() {
        return bet;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void dealCard()
    {
        playerHand.receiveCard(deck.surrenderCard());
    }


    public void dealHand()
    {
        for (int i = 0; i < 5; i++)
            dealCard();
    }

    public Hand showPlayerHand() {
        return playerHand;
    }

    public void pay(Player player, double payOut)
    {
        player.collectWinnings(payOut);
        UserInput.display("You win " + payOut + "!\nWallet: " + player.getWallet());
    }

    public void play(Player player)
    {
        deck.buildAnotherDeck();

        //get bet
        boolean quit = false;
        while(!quit){
            quit = bet.makeBet(UserInput.getDoubleInput("How much do you want to bet?"), player);
        }

        //deal cards to player
        dealHand();

        //display
        String output = userDisplayHand(playerHand);
        UserInput.display(output);
        //discard cards
        int numCardsToDiscard;
        numCardsToDiscard = getIntInput("How many cards do you want to discard? ");
        ArrayList<Integer> discardIndexes = new ArrayList<>(numCardsToDiscard);
        for (int i = 0; i < numCardsToDiscard; i++)
        {
            double getDiscard = getDoubleInput("Please enter the index of the card that is to be discarded: ");
            discardIndexes.add((int) getDiscard);
        }

        discardCards(discardIndexes, playerHand);

        //replace discarded cards
        replace(numCardsToDiscard);

        //display new hand
        UserInput.display("Updated Hand: ");
        output = userDisplayHand(playerHand);
        UserInput.display(output);

        //calculate hand
        String rankOfHand = calculateHand();
        UserInput.display("You got a " + rankOfHand);

        // determine winnings and pay
        payPlayer(player, rankOfHand);

        playerHand.remove();

        if(player.getWallet() > 20){
            askPlayAgain(player);
        } else {
            UserInput.display("You do not have enough chips to continue, returning to main menu.\n");
        }
    }

    public void payPlayer(Player player, String rankOfHand)
    {
        switch (rankOfHand)
        {
            case "PAIR":
                pay(player, bet.getBetValue());
                break;
            case "TWO PAIR":
                pay(player, bet.getBetValue() * 2);
                break;
            case "THREE OF A KIND":
                pay(player, bet.getBetValue() * 3);
                break;
            case "STRAIGHT":
                pay(player, bet.getBetValue() * 4);
                break;
            case "FLUSH":
                pay(player, bet.getBetValue() * 6);
                break;
            case "FULL HOUSE":
                pay(player, bet.getBetValue() * 9);
                break;
            case "FOUR OF A KIND":
                pay(player, bet.getBetValue() * 25);
                break;
            case "STRAIGHT FLUSH":
                pay(player, bet.getBetValue() * 50);
                break;
            case "ROYAL FLUSH":
                pay(player, bet.getBetValue() * 976);
                break;
            case "NO PAIR":
                UserInput.display("Sorry, you lose!\nWallet: " + player.getWallet());
                break;
            default:
                break;
        }
    }

    public void askPlayAgain(Player player)
    {
        String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit)");
        if (choice.equalsIgnoreCase("Y"))
            play(player);
        else
            UserInput.display("Thanks for playing!\n\n");
    }

    public String calculateHand()
    {
        int numberOfValues = returnNumberOfValuesInPlayerHand();
        if (numberOfValues == 5)
        {
            return evaluateFiveRanks();
        } else if (numberOfValues == 4)
        {
            return "PAIR";
        } else if (numberOfValues == 3)
        {
            return evaluateThreeRanks();
        } else
        {
            return evaluateTwoRanks();
        }
    }

    public String evaluateFiveRanks()
    {
        if (checkRoyalFlush())
            return "ROYAL FLUSH";
        else if (checkStraightFlush())
            return "STRAIGHT FLUSH";
        else if (checkFlush())
            return "FLUSH";
        else if (checkStraight())
            return "STRAIGHT";
        else
            return "NO PAIR";
    }

    public String evaluateTwoRanks()
    {
        ArrayList<Card> hand = playerHand.getCards();
        Map<Integer, Integer> mapOfHand = toHashMap(hand);
        for (Integer key : mapOfHand.keySet())
        {
            if (mapOfHand.get(key) == 4) return "FOUR OF A KIND";
        }
        return "FULL HOUSE";
    }

    public String evaluateThreeRanks()
    {
        ArrayList<Card> hand = playerHand.getCards();
        Map<Integer, Integer> mapOfHand = toHashMap(hand);
        for (Integer key : mapOfHand.keySet())
        {
            if (mapOfHand.get(key) == 3) return "THREE OF A KIND";
        }
        return "TWO PAIR";
    }

    public Map<Integer, Integer> toHashMap(ArrayList<Card> playerHand)
    {
        Map<Integer, Integer> mapOfValues = new HashMap<>();

        for (int i = 0; i < playerHand.size(); i++)
        {
            Integer key = playerHand.get(i).getValue();
            Integer frequency = mapOfValues.get(key);
            mapOfValues.put(key, frequency == null ? 1 : frequency + 1);
        }
        return mapOfValues;
    }

    public int returnNumberOfValuesInPlayerHand()
    {
        Map<Integer, Integer> mapOfValues = toHashMap(playerHand.getCards());
        Integer amountOfKey = mapOfValues.keySet().size();
        return amountOfKey;
    }

    public boolean checkRoyalFlush()
    {
        ArrayList<Card> playerCards = playerHand.getCards();
        Collections.sort(playerCards);

        if (returnNumberOfValuesInPlayerHand() == 5 && (playerCards.get(0).getValue() == 10) && checkFlush())
        {
            return true;
        }
        return false;
    }

    public boolean checkStraight()
    {
        ArrayList<Card> playerCards = playerHand.getCards();
        Collections.sort(playerCards);
        int firstCard = playerCards.get(0).getValue();
        int fourthCard = playerCards.get(3).getValue();
        int fifthCard = playerCards.get(4).getValue();
        if(fifthCard - firstCard == 4){
            return true;
        } else if((firstCard == 2 && fourthCard == 5) && fifthCard == 14){
            return true;
        } else return false;
    }

    public boolean checkStraightFlush()
    {

        if (checkFlush() && checkStraight())
        {
            return true;
        }
        return false;
    }

    public boolean checkFlush()
    {
        ArrayList<Card> playerCards = playerHand.getCards();
        String myString = playerCards.get(0).getSuit();

        for (int i = 1; i < 5; i++)
        {
            if (!playerCards.get(i).getSuit().equals(myString))
                return false;
        }
        return true;
    }

    public void replace(int numberToReplace)
    {
        for (int i = 0; i < numberToReplace; i++)
            dealCard();
    }




    public void discardCards(ArrayList<Integer> discardIndexes, Hand playerHand)
    {
        Collections.sort(discardIndexes);
        //Hand cloneOfHand = playerHand.getCards();
        for (int j = discardIndexes.size() - 1; j >= 0; j--)
        {
            playerHand.remove(discardIndexes.get(j) - 1);
        }
    }

    public String userDisplayHand(Hand playerHand)
    {
        StringBuilder outputSB = new StringBuilder(1000);
        String output;
        ArrayList<Card> cards = playerHand.getCards();
        for (int i = 0; i < cards.size(); i++)
        {
            outputSB.append("[" + (i + 1) + "]: " + cards.get(i) + "\n");
        }
        output = outputSB.toString();
        return output;
    }

}
