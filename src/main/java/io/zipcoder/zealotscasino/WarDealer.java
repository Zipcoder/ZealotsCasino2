package io.zipcoder.zealotscasino;

/**
 * Created by andrewwong on 5/10/17.
 */
public class WarDealer implements CardDealer {
    private Deck deck;
    private Card card;
    public WarDealer(){
        deck = new Deck();
        deck.buildDeck();
    }
    public void dealCardTo(Player player){
        Hand playersHand = player.getHand();
        Card cardDealt = deck.surrenderCard();
        playersHand.receiveCard(cardDealt);
    }

    public void dealHandTo(Player player) {
        dealCardTo(player);
    }

    public void takeTurn() {

    }

    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void play(WarDealer dealer){

    }

}
