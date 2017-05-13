package io.zipcoder.zealotscasino;

/**
 * Created by aaronlong on 5/13/17.
 */
public class BlackJackHand extends Hand {
    private int handValue = 0;

    public int getPlayerHandValue() {
        return handValue;
    }

    public int examineHandValue() {
        for(Card card : getCards()) {
            handValue += examineCardValue(card);
        }
        for(Card card : getCards()){
            if(extractCardValue(card) == 12 && handValue > 21){
                handValue -= 10;
            }
        }
        return handValue;
    }

    private int examineCardValue(Card card) {
        int cardValue = 0;
        int cardOrdinal = extractCardValue(card);
        if (cardOrdinal == 12) {
            cardValue += examineAceValue();
        } else if (cardOrdinal > 8 && cardOrdinal < 12) {
            cardValue += 10;
        } else if (cardOrdinal <= 8) {
            cardValue += Card.CardValue.valueOf(card.getFaceValue()).ordinal() + 2;
        }
        return cardValue;
    }

    private int examineAceValue(){
        int aceVal = 0;
        if(handValue > 10){
            aceVal++;
        } else {
            aceVal += 11;
        }
        return aceVal;
    }

    private int extractCardValue(Card card){
        return Card.CardValue.valueOf(card.getFaceValue()).ordinal();
    }

    public boolean checkBust(){
        if(examineHandValue() > 21) {
            return true;
        }
        return false;
    }

    public boolean checkBlackJack() {
        if(examineHandValue() == 21){
            return true;
        }
        return false;
    }
}
