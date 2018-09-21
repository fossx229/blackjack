import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	Scanner scan = new Scanner(System.in);
	boolean playing;
	Dealer dealer;
	Player player;
	boolean push;
	boolean blackJack;
	boolean testing = false;

	/**
	 * Game constructor
	 * 
	 */
	public Game() {
		this.dealer = new Dealer();
	}

	/**
	 * playBlackJack runs most the code, it asks a player to bet
	 * dealer deals cards
	 * checkBlackJack checks if either the deal or player has blackjack
	 * Player will decide to hit or to stand
	 * Dealer will decide to hit or to stand
	 * resolveGame will see if the dealer or the player won/lose/tied
	 * 
	 */
	public void playBlackJack() {
		player.Bet();
		dealer.dealCards(this.player);
		checkBlackJack();
		playerHitOrNot();
		dealerHitOrNot();
		resolveGame();
	}

	/**
	 * gameSetUp makes the player and reads in stats from previous games
	 * testing flag allows tests to not fail
	 */
	public void gameSetUp() {
		boolean running = true;
		while(running) {
			try {
				System.out.println("Hello! What is your name?");
				String name = scan.nextLine();;
				this.player = new Player(10000, name);

				if(this.player != null) {
					running = false;
				}


			} catch (Exception e) { 
				System.out.println("You need to enter valid input. You probably want to enter a valid bet number or name or something went wrong reading in stats.");
			}

		}
	}

	/**
	 * Checks if either player has 21 right away and updates wins, loses, and ties.
	 */
	public void checkBlackJack() {
		this.player.getHand().checkForAce();
		this.dealer.getHand().checkForAce();
		if(player.getHand().getCount() == 21 && dealer.getHand().getCount() == 21) {
			player.Push();
			player.updateTies();
			keepPlaying();
		} else if(player.getHand().getCount() == 21) {
			System.out.println("BlackJack!");
			System.out.println("your hand is:");
			this.player.getHand().printHand();
			this.player.addToBank(1.5);
			System.out.println("You win!! " + player.getWinnings(1.5) + " dollars");
			this.player.updateBlackJackNumber();
			this.player.updateWins();
			this.dealer.updateLoses();
			keepPlaying();
		} else if(dealer.getHand().getCount() == 21) {
			System.out.println("Dealer has BlackJack, Dealer wins!");
			System.out.println("Their hand was: ");
			this.dealer.getHand().printHand();
			System.out.println("You lost: " + player.getBet());
			this.dealer.updateBlackJackNumber();;
			this.player.updateLoses();
			this.player.removeFromBank();
			keepPlaying();
		}
	}

	/**
	 * Checks if the player went over 21, updates an ace if to count as 11 if ace count is less than
	 * one, updates ace count back to 1 if more than one ace exists, method returns if player choses to stand
	 * updates wins, money, and loses too
	 */
	public void playerHitOrNot () {
		if(checkBust()) {
			if(this.player.getHand().getAceCount() < 1 && this.player.getHand().getCount() < 11) {
				this.player.getHand().checkForAce();
			}
			if(this.player.getHand().getAceCount() > 2 && this.player.getHand().getCount() > 22) {
				this.player.getHand().resetAce();
			}
			System.out.println("The dealer's face-up card is: ");
			this.dealer.getHand().printCard();
			System.out.println("your hand is:");
			player.getHand().printHand();
			System.out.println("Your hand value is: " + player.getHand().getCount());
			System.out.println("Would like to hit or stand?");
			Scanner scan2 = new Scanner(System.in);
			String answer = scan2.nextLine();
			answer.toLowerCase();

			if(answer.compareTo("hit") == 0) {
				this.player.getHand().addToHand(this.dealer.dealCard());
				System.out.println("You hit!");
				System.out.println("Your hand is now:");
				this.player.getHand().printHand();
				System.out.println("with a count of: " + this.player.getHand().getCount());
				playerHitOrNot();
			} else return;
		} else { 
			System.out.println("Your hand is: ");
			this.player.getHand().printHand();
			System.out.println("Your count is: " + this.player.getHand().getCount());
			System.out.println("You bust, you lose!! " + this.player.getBet());
			this.player.updateLoses();
			this.dealer.updateWins();
			this.player.removeFromBank();
			keepPlaying();
		}


	}

	/**
	 * Checks if the dealer or player went over 21.
	 * 
	 */
	public boolean checkBust() {
		if(this.player.getHand().getCount()>21 ) {
			return false;
		} else if (this.dealer.getHand().getCount()>21) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if the dealer went over 21, updates an ace if to count as 11 if ace count is less than
	 * one, updates ace count back to 1 if more than one ace exists, method returns if player choses to stand
	 * updates wins, money, and loses too
	 */
	public void dealerHitOrNot() {
		if(this.dealer.getHand().getAceCount() < 1 && this.dealer.getHand().getCount() < 12) {
			this.dealer.getHand().checkForAce();
		}
		if(this.dealer.getHand().getAceCount() > 2 && this.dealer.getHand().getCount() > 22) {
			this.dealer.getHand().resetAce();
		}
		if(checkBust()) {
			if(this.dealer.getHand().getCount() <= 16 || this.dealer.getHand().getCount() < this.player.getHand().getCount()) {
				this.dealer.getHand().addToHand(this.dealer.dealCard());
				System.out.println("Dealer hits!");
				System.out.println("Their hand is now:");
				this.dealer.getHand().printHand();
				System.out.println("with a count of: " + this.dealer.getHand().getCount());
				dealerHitOrNot();
			} else return;
		} else {
			this.player.updateWins();
			this.dealer.updateLoses();
			this.player.addToBank(1);
			System.out.println("Dealer's hand is: ");
			this.dealer.getHand().printHand();
			System.out.println("The dealer's count is: " + this.dealer.getHand().getCount());
			System.out.println("Dealer busts, you win!! " + player.getWinnings(1));
			keepPlaying();
		}

	}

	/**
	 * compares dealer and player hand value and updates wins, loses, ties, and money.
	 */
	public void resolveGame() {
		if(this.player.getHand().getCount() == this.dealer.getHand().getCount()) {
			this.player.Push();
			this.player.updateTies();
			keepPlaying();
		} else if(this.player.getHand().getCount() > this.dealer.getHand().getCount()) {
			this.player.updateWins();
			this.dealer.updateLoses();
			player.addToBank(1);
			System.out.println("The dealer has a count of :" + this.dealer.getHand().getCount() + " Your hand is:");
			this.player.getHand().printHand();
			System.out.println("The dealers count was: " + this.dealer.getHand().getCount());
			System.out.println("The dealers hand was: ");
			this.dealer.getHand().printHand();
			System.out.println("You win!! " + player.getWinnings(1));
			keepPlaying();


		} else if(this.player.getHand().getCount() < this.dealer.getHand().getCount()) {
			this.player.updateLoses();
			this.dealer.updateWins();
			this.player.removeFromBank();
			System.out.println("The dealers hand was: ");
			this.dealer.getHand().printHand();
			System.out.println("Dealer wins!! Dealer had: " + this.dealer.getHand().getCount() + " You had: " + this.player.getHand().getCount() );
			System.out.println("You lost: " + this.player.getBet());
			keepPlaying();
		}
	}

	/**
	 * method for replaying the game
	 */
	public void keepPlaying() {
		if(testing) return; 
		else {
			if(this.dealer.getDeck().getSize() < 26) {
				this.dealer.remakeDeck();
			}
			System.out.println("Would you like to play again or see your lifetime stats? (enter 'yes' to keep playing or 'no' to stop playing or 'stats' to see lifetime stats");
			String answer = scan.nextLine();
			if(answer.toLowerCase().compareTo("yes")==0) {
				this.player.getHand().clear();
				this.dealer.getHand().clear();
				playBlackJack();
			} else if (answer.toLowerCase().compareTo("no")==0) {
				System.out.println("Thanks for playing!!");
				writeData();
				System.exit(0);

			} else if(answer.toLowerCase().compareTo("stats")==0) {
				writeData();
				readDataIn();
				printStats();
				keepPlaying();

			} else {
				keepPlaying();
			}
		}
	}

	/**
	 * writes stat data to a text file.
	 */
	public void writeData() {
		try {
			PrintWriter writer = new PrintWriter("blackjack.txt", "UTF-8");
			writer.println("Player wins: " + this.player.wins);
			writer.println("Player loses: " + this.player.loses);
			writer.println("Number of ties: " + this.player.numberTies);
			writer.println("Number of times player got blackjack: " + this.player.blackJackNumber);
			writer.println("Dealer Wins: " + this.dealer.loses);
			writer.println("Dealer loses: " + this.dealer.loses);
			writer.println("Number of times dealer got blackjack: " + this.dealer.blackJackNumber);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printStats() {
		System.out.println("Dealer's lifetime stats:");
		System.out.println("number of times dealer got blackjack: " + this.dealer.blackJackNumber);
		System.out.println("number of times dealer won: " + this.dealer.wins);
		System.out.println("number of times dealer lost: " + this.dealer.loses);
		System.out.println();
		System.out.println("Player's lifetime stats:");
		System.out.println("number of times player got blackjack: " + this.player.blackJackNumber);
		System.out.println("number of times player won: " + this.player.wins);
		System.out.println("number of times player lost: " + this.player.loses);
		System.out.println("number of times player tied: " + this.player.numberTies);

	}
	
	public void readDataIn() {
		if(testing == false) {
			try {
				this.player.readStats();
				this.dealer.readStats();
				this.player.setStats();
				this.dealer.setStats();
			} catch (Exception e) {

			}
		}
	}
}


