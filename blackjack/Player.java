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
	public Player(int bank, String name) {
		this.bank = bank;
		this.name = name;
		this.playersHand = new Hand();
	}

	public void Bet() {
		System.out.println("You have " + this.bank + " to bet with.");
		System.out.println("Please enter the amount you would like to bet");
		Scanner scan = new Scanner(System.in);
		int amountToBet = scan.nextInt();

		if(this.bank - amountToBet > 0) {
			this.bet = amountToBet;
		} else {
			System.out.println(this.name + " Only has " + this.bank + " left to bet!");
			System.out.println("Please enter a valid amount.");
			Bet();
		}
	}



	public void Push() {
		System.out.println("Push, no one wins!");
		this.bank = this.bank + this.bet;
	}

	public void addToBank (double multi) {
		this.bank = this.bank + ((this.bet * multi) + this.bet);
	}

	public double getBet() {
		return this.bet;
	}

	public double win(double multi) {
		return this.bank + ((this.bet * multi) + this.bet);
	}

	public double getWinnings(double multi) {
		return ((this.bet * multi) + this.bet);
	}

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

	public void readStats() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("blackjack.txt"));
			while (in.read()!= -1) {
				stats.add(in.readLine());
			}
			in.close();
		} 
		catch (IOException e) {
			System.out.println("Something went wrong when trying to read a file.");
		}
	}

	public void setStats() {
		this.wins = Integer.parseInt(stats.get(0).substring(stats.get(0).length()-1, stats.get(0).length()));
		this.loses = Integer.parseInt(stats.get(1).substring(stats.get(1).length()-1, stats.get(1).length()));
		this.blackJackNumber = Integer.parseInt(stats.get(2).substring(stats.get(2).length()-1, stats.get(2).length()));
		this.numberTies = Integer.parseInt(stats.get(3).substring(stats.get(3).length()-1, stats.get(3).length()));
	}
}

