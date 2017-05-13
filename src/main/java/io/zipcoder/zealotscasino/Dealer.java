package io.zipcoder.zealotscasino;

/**
 * Created by luisgarcia on 5/9/17.
 */
public interface Dealer
{
    void pay(Player player, double payOut);

    void play(Player player);
}
