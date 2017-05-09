package io.zipcoder.zealotscasino;

/**
 * Created by luisgarcia on 5/9/17.
 */
public interface CardDealer
{
    void dealCardTo(Player player);

    void dealHandTo(Player player);

    void takeTurn();
}
