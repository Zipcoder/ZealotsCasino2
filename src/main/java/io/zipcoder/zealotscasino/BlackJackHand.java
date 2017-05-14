package io.zipcoder.zealotscasino;

/**
 * Created by aaronlong on 5/13/17.
 */
public class BlackJackHand extends Hand {

    private int handValue = 0;

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue){
        this.handValue = handValue;
    }

    public void updateHandValue() {
        for(Card card : getCards()) {
            handValue += evaluateCard(card);
        }
        for(Card card : getCards()){
            if(extractCardOrdinal(card) == 12 && handValue > 21){
                handValue -= 10;
            }
        }
    }

    private int evaluateCard(Card card) {
        int cardValue = 0;
        int cardOrdinal = extractCardOrdinal(card);
        if (cardOrdinal == 12) {
            cardValue += evaluateAce();
        } else if (cardOrdinal > 8 && cardOrdinal < 12) {
            cardValue += 10;
        } else if (cardOrdinal <= 8) {
            cardValue += Card.CardValue.valueOf(card.getFaceValue()).ordinal() + 2;
        }
        return cardValue;
    }

    private int evaluateAce(){
        int aceVal = 0;
        if(handValue > 10){
            aceVal++;
        } else {
            aceVal += 11;
        }
        return aceVal;
    }

    private int extractCardOrdinal(Card card){
        return Card.CardValue.valueOf(card.getFaceValue()).ordinal();
    }

    public boolean checkIfBust(){
        if(getHandValue() > 21) {
            return true;
        }
        return false;
    }

    public boolean checkIfBlackJack() {
        if(getHandValue() == 21){
            return true;
        }
        return false;
    }
}
