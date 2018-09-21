import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dealer {
	int wins;
	int loses;
	int blackJackNumber;
	private int count;
	private Hand dealersHand = new Hand();
	private int numberOfDecks;
	private static Deck dealersDeck;
	ArrayList<String> stats = new ArrayList<String>();
	
	/**
	 * Deck constructor
	 */
	public Dealer() {
		this.dealersDeck = new Deck();
		this.dealersDeck.shuffle();
	}

	/**
	 * @param player
	 * Deals cards to a player
	 */
	public void dealCards(Player player) {

		for(int j=0; j<2; j++) {
			player.getHand().addToHand(dealCard());
		}
		
		this.dealersHand.addToHand(dealCard());
		this.dealersHand.addToHand(dealCard());
	}

	/**
	 * Deals a card
	 */
	public Card dealCard() {
		return dealersDeck.playCard();
	}
	
	/**
	 * Getter for a hand
	 */
	public Hand getHand() {
		return this.dealersHand;
	}

	public void updateLoses() {
		this.loses = loses +1;
	}
	
	public void updateWins() {
		this.wins = wins +1;
	}
	
	public void updateBlackJackNumber() {
		this.blackJackNumber = this.blackJackNumber + 1;
	}
	
	public void remakeDeck() {
		this.dealersDeck = new Deck();
		this.dealersDeck.shuffle();
	}
	
	public Deck getDeck() {
		return this.dealersDeck;
	}
	
	/**
	 * reads a text file for a dealer's lifetime stats
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
			System.out.println("Something went wrong when trying to read a file.");
		}
	}
	
	/**
	 * sets stats for a dealer
	 */
	public void setStats() {
		this.wins = Integer.parseInt(stats.get(4).substring(stats.get(4).length()-1, stats.get(4).length()));
		this.loses = Integer.parseInt(stats.get(5).substring(stats.get(5).length()-1, stats.get(5).length()));
		this.blackJackNumber = Integer.parseInt(stats.get(6).substring(stats.get(6).length()-1, stats.get(6).length()));
		
	}
}
