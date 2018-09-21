import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private double bank;
	private String name;
	int wins;
	int loses;
	int blackJackNumber;
	int numberTies;
	double bet;
	private Hand playersHand;
	ArrayList<String> stats = new ArrayList<String>();
	
	/**
	 * Player constructor
	 * @param bank - amount of money the player starts with
	 * @param name - the name of the player
	 * 
	 */
	public Player(int bank, String name) {
		
		this.bank = bank;
		this.name = name;
		this.playersHand = new Hand();
	}

	/**
	 * Bet allows the player to enter in the amount he or she want to bet.
	 * 
	 */
	public void Bet() {
		System.out.println("You have " + this.bank + " to bet with.");
		System.out.println("Please enter the amount you would like to bet");
		Scanner scan = new Scanner(System.in);
		int amountToBet = scan.nextInt();

		if(this.bank - amountToBet >= 0) {
			this.bet = amountToBet;
		} else {
			System.out.println(this.name + " Only has " + this.bank + " left to bet!");
			System.out.println("Please enter a valid amount.");
			Bet();
		}
	}


	/**
	 * Push gives the player back their money in the event of a tie.
	 * 
	 */
	public void Push() {
		System.out.println("Push, no one wins!");
		this.bank = this.bank + this.bet;
	}

	/**
	 * @param multi allows to get the correct amount of money won when odds are not 1:1
	 * 
	 */
	public void addToBank (double multi) {
		this.bank = this.bank + ((this.bet * multi) + this.bet);
	}

	/**
	 * getter for bet
	 * @return bet
	 */
	public double getBet() {
		return this.bet;
	}


	/**
	 * getter for winnings
	 * @return winnings
	 * @param multi allows to get the correct amount of money won when odds are not 1:1
	 */
	public double getWinnings(double multi) {
		return ((this.bet * multi) + this.bet);
	}

	/**
	 * getter for hand
	 * @return hand
	 */
	public Hand getHand() {
		return this.playersHand;
	}

	public void updateLoses() {
		this.loses = loses +1;
	}

	public void updateWins() {
		this.wins = wins +1;
	}

	public void updateTies() {
		this.numberTies = numberTies +1;
	}

	public void updateBlackJackNumber() {
		this.blackJackNumber = this.blackJackNumber + 1;
	}

	public void removeFromBank() {
		this.bank = this.bank - this.bet;
	}
 
	/**
	 * reads stats in for the player or throw an exception if something went wrong.
	 * 
	 */
	public void readStats() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("blackjack.txt"));
			while (in.read()!= -1) {
				stats.add(in.readLine());
			}
			in.close();
		} 
		catch (IOException e) {
			System.out.println("Something went wrong when trying to read a file for the player");
		}
	}

	/**
	 * sets stats for the player from previous games
	 * 
	 */
	public void setStats() {
		
		for(int i = 0; i < stats.size(); i++) {
			stats.set(i, stats.get(i).replaceAll("[^-?0-9]+", ""));
		}
		System.out.println(stats.get(0));
		this.wins = Integer.parseInt(stats.get(0));
		this.loses = Integer.parseInt(stats.get(1));
		this.blackJackNumber = Integer.parseInt(stats.get(2));
		this.numberTies = Integer.parseInt(stats.get(3));
	}
}

