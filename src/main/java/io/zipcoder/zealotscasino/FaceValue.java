package io.zipcoder.zealotscasino;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public enum FaceValue {
    KING("K"), QUEEN("Q"), JACK("J"), ACE("A");

    private String val;

    FaceValue(String theVal) {
        val = theVal;
    }

    public String getVal(){
        return val;
    }

    public int cardVal() {
        int numberVal = 0;
        switch(val) {
            case "K":
                numberVal = 10;
                break;
            case "Q":
                numberVal = 10;
                break;
            case "J":
                numberVal = 10;
                break;
            case "A":
                numberVal = 11;
                break;

        }
        return numberVal;
    }

}
