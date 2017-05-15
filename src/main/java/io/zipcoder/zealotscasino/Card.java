package io.zipcoder.zealotscasino;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public class Card implements Comparable<Card>{

    public static final String ANSI_RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    private String suit;
    private String faceValue;

    public enum Suit {SPADES, DIAMONDS, HEARTS, CLUBS}
    public enum CardValue {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

    public Card(){}

    public Card(String faceValue, String suit){
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return Card.CardValue.valueOf(getFaceValue()).ordinal() + 2;
    }

    public String getFaceValue() {
        if (faceValue == null) return "";
        return faceValue;
    }

    public String getFaceToPrint()
    {
        if(getValue() > 10)
        {
            if(getValue() == 11)
            {
                return getFaceValue().substring(0,1);
            }
            else if(getValue() == 12)
            {
                return getFaceValue().substring(0,1);
            }
            else if(getValue() == 13)
            {
                return getFaceValue().substring(0,1);
            }
            else
            {
                return getFaceValue().substring(0,1);
            }
        }
        else
            return getValue()+"";
    }

    public String getSuitSymbol()
    {
        if(suit.equals("SPADES"))
            return ((char)'\u2660' + " ");
        else if(suit.equals("CLUBS"))
            return ((char)'\u2663' + " ");
        else if(suit.equals("DIAMONDS"))
            return (ANSI_RED + '\u2666' + " " + RESET);
        else if(suit.equals("HEARTS"))
            return (ANSI_RED+ '\u2764' + " " + RESET);
        else
            return "Invalid Symbol";

    }

    public void setFaceValue(String theFaceValue) {
        faceValue = theFaceValue;
    }

    public int compareTo(Card c){
        return Card.CardValue.valueOf(getFaceValue()).ordinal() - Card.CardValue.valueOf(c.getFaceValue()).ordinal();
    }

    public String toString(){
        return String.format("| %-2s %2s|", getFaceToPrint(), getSuitSymbol());
    }



}
