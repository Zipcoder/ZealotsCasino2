package io.zipcoder.zealotscasino;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by andrewwong on 5/9/17.
 */
public class Deck {
    private ArrayList<Card> deckQue = new ArrayList<>();

    public ArrayList<Card> getDeckQue() {
        return deckQue;
    }

    public void buildDeck(){
        for(Card.Suit suit: Card.Suit.values()){
            for(Card.CardValue cardValue : Card.CardValue.values()){
                Card card = new Card(cardValue.name(), suit.name());
                deckQue.add(card);
            }
        }
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(deckQue);
    }

    public Card surrenderCard(){
        Card card = deckQue.get(0);
        deckQue.remove(0);
        return card;
    }

    public void buildAnotherDeck()
    {
        if(deckQue.size() <= 5)
        {
            buildDeck();
        }
    }
}
