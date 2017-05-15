package io.zipcoder.zealotscasino;



/**
 * Created by luisgarcia on 5/10/17.
 */
public class Game
{
    private Player player;
    private Dealer dealer;
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
            case 4:
                return new BigSixDealer();
            default:
                UserInput.display("Invalid Entry. Try Again");
                return null;
        }
    }

    private void play(Dealer dealer) {
        if (dealer != null) {
            dealer.play(player);
            displayMenu();
        } else chooseGame();
    }


    public void chooseGame()
    {
        int gameChoice;
        if(Bet.MINIMUM_BET > player.getWallet()) {
            UserInput.display("Just kidding, you broke fam");
            return;
        }
        else{
            gameChoice = user.getIntInput("Choose your # of choice: ");
        }

        dealer = makeDealer(gameChoice);
        play(dealer);
    }

    public void displayIntro()
    {
        UserInput.display("--------------------Zealot's Casino--------------------");
        double startWallet = UserInput.getDoubleInput("How much money do you want to start your wallet with?");
        try{
            System.out.println(startWallet);
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
