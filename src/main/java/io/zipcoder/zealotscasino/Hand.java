package io.zipcoder.zealotscasino;

import java.util.ArrayList;

/**
 * Created by aaronlong on 5/9/17.
 */
public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void receiveCard(Card newCard) {
        cards.add(newCard);
    }

    public void receiveCards(ArrayList<Card> theCards) {
        cards.addAll(theCards);
    }

    public ArrayList<Card> getCards() {
        return (ArrayList<Card>) cards.clone();
    }

    public void remove(int index) {
        cards.remove(index);
    }

}
