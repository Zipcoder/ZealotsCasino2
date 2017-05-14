package io.zipcoder.zealotscasino;



/**
 * Created by luisgarcia on 5/10/17.
 */
public class Game
{
    private Player player;
    private CardDealer dealer;
    private BigSixDealer bigSixDealer;
    UserInput user;

    public Game(Player player){
        this.player = player;
        user = new UserInput();
    }


    public void playBlackJack()
    {
        dealer = new BlackJackDealer();
        dealer.play(player);
        displayMenu();
    }

    public void playWar()
    {
        dealer = new WarDealer();
        dealer.play(player);
        displayMenu();
    }

    public void playPoker()
    {
        dealer = new PokerDealer();
        dealer.play(player);
        displayMenu();
    }
/*
    public void playRoulette()
    {
        dealer = new RouletteDealer();
    }*/

    public void playBigSix()
    {
        bigSixDealer = new BigSixDealer();
        bigSixDealer.play(player);
        displayMenu();
    }


    public void chooseGame()
    {
        double gameChoice;
        if(player.getWallet() < player.getMinimumBet()){
            UserInput.display("Just kidding, you broke fam");
            gameChoice = 6;
        }
        else{
            gameChoice = user.getDoubleInput("Choose your # of choice: ");
        }

        if(gameChoice == 1)
        {
            this.playWar();
        }
        else if(gameChoice == 2)
        {
            this.playBlackJack();
        }
        else if(gameChoice == 3)
        {
            this.playPoker();
        }
        /*else if(gameChoice == 4)
        {
            this.playRoulette();
        }*/
        else if(gameChoice == 5)
        {
            this.playBigSix();
        }
        else if(gameChoice == 6)
        {
            UserInput.display("Thanks for playing!");
        }
        else
        {
            UserInput.display("Invalid Entry. Try Again");
            this.chooseGame();
        }
    }

    public void displayIntro()
    {
        UserInput.display("--------------------Zealot's Casino--------------------");
        double startWallet = UserInput.getDoubleInput("How much money do you want to start your wallet with?");
        try{
            player.initializeWallet(startWallet);
        }catch(IllegalArgumentException e){
            UserInput.display("Must have at least $20 to enter Zealot's Casino.");
            displayIntro();
        }
        //Game myGame = new Game(player);
    }


    public void displayMenu()
    {
        UserInput.display("What would you like to play?");
        UserInput.display("(1) War");
        UserInput.display("(2) Blackjack");
        UserInput.display("(3) Poker");
        UserInput.display("(4) Roulette");
        UserInput.display("(5) BigSix");
        UserInput.display("(6) Quit");
        this.chooseGame();
    }

}
