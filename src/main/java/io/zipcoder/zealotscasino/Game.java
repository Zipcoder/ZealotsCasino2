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
            System.out.println("Just kidding, you broke fam");
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
            System.out.println("Thanks for playing!");
        }
        else
        {
            System.out.println("Invalid Entry. Try Again");
            this.chooseGame();
        }
    }

    public void displayIntro()
    {
        System.out.println("--------------------Zealot's Casino--------------------");
        double startWallet = UserInput.getDoubleInput("How much money do you want to start your wallet with?");
        try{
            player.initializeWallet(startWallet);
        }catch(IllegalArgumentException e){
            System.out.println("Must have at least $20 to enter Zealot's Casino.");
            displayIntro();
        }
        //Game myGame = new Game(player);
    }


    public void displayMenu()
    {
        System.out.println("What would you like to play?");
        System.out.println("(1) War");
        System.out.println("(2) Blackjack");
        System.out.println("(3) Poker");
        System.out.println("(4) Roulette");
        System.out.println("(5) BigSix");
        System.out.println("(6) Quit");
        this.chooseGame();
    }

}
