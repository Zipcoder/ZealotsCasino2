package io.zipcoder.zealotscasino;



/**
 * Created by luisgarcia on 5/10/17.
 */
public class Game
{
    private Player player;
    private Dealer dealer;
    private BigSixDealer bigSixDealer;
    UserInput user;

    public Game(Player player){
        //this.player = player;
        user = new UserInput();
    }

    public static Dealer makeDealer(int dealer) {
        switch(dealer) {
            case 1:
                return new WarDealer();
            case 2:
                return new BlackJackDealer();
            case 3:
                return new PokerDealer();
            /*case 6:
                UserInput.display("Thanks for playing!");
                break; */
            default:
                UserInput.display("Invalid Entry. Try Again");
                return null;
        }
    }

    private void play(Dealer dealer) {
        dealer.play(player);
        displayMenu();
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
        if(player.getWallet() < Bet.MINIMUM_BET){
            UserInput.display("Just kidding, you broke fam");
            gameChoice = 6;
        }
        else{
            gameChoice = user.getIntInput("Choose your # of choice: ");
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
            player = new Player(startWallet);
        }catch(IllegalArgumentException e){
            UserInput.display("Must have at least $20 to enter Zealot's Casino.");
            displayIntro();
        }
        //Game myGame = new Game(player);
    }


    public void displayMenu()
    {
        String displayPrompt = "What would you like to play?\n"
                        + "(1) War\n"
                        + "(2) Blackjack\n"
                        + "(3) Poker\n"
                        + "(4) BigSix\n";
        UserInput.display(displayPrompt);
        this.chooseGame();
    }

}
