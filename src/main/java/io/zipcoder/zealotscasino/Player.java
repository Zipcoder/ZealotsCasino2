package io.zipcoder.zealotscasino;

/**
 * Created by luisgarcia on 5/9/17.
 */
public class Player
{
    private double bet;
    private double wallet;
    private Hand hand;

    public Player(){
        hand = new Hand();

    }
    public double getBet() {
        return bet;
    }

    public void makeBet(double bet) {
        try{
            if(bet>wallet) {
                throw new IllegalArgumentException("Insufficient Funds");
            }
                this.bet = bet;
                this.wallet = wallet - bet;
        }catch(IllegalArgumentException e){
            System.out.println("Insufficient funds");
        }


    }

    public double getWallet() {
        return wallet;
    }

    public void initializeWallet(double initialWallet) {
        this.wallet = initialWallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void collectWinnings(double winnings){
        wallet += winnings;
    }
}
