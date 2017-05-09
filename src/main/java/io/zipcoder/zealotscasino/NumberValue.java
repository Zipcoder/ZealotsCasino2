package io.zipcoder.zealotscasino;

/**
 * Created by andrewwong on 5/9/17.
 */
public enum NumberValue {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10");

    private String val;

    NumberValue(String theVal) {
        val = theVal;
    }

    public String getVal(){
        return val;
    }

    public int cardVal() {
        int numberVal = 0;
        switch(val) {
            case "2":
                numberVal = 2;
                break;
            case "3":
                numberVal = 3;
                break;
            case "4":
                numberVal = 4;
                break;
            case "5":
                numberVal = 5;
                break;
            case "6":
                numberVal = 6;
                break;
            case "7":
                numberVal = 7;
                break;
            case "8":
                numberVal = 8;
                break;
            case "9":
                numberVal = 9;
                break;
            case "10":
                numberVal = 10;


        }
        return numberVal;
    }
}
