import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private double bank;
	private String name;
	private int wins;
	private int loses;
	private int blackJackNumber;
	private double bet;
	private int count = 0;
	private ArrayList<Card> playersHand = new ArrayList<Card>();

	public Player(int bank, String name) {
		this.bank = bank;
		this.name = name;
	}

	public void Bet() {
		System.out.println("Please enter the amount you would like to bet");
		Scanner scan = new Scanner(System.in);
		int amountToBet = scan.nextInt();

		if(this.bank - amountToBet > 0) {
			this.bet = amountToBet;
		} else {
			System.out.println(this.name + " Only has " + this.bank + " left to bet!");
			Bet();
		}
	}

	public void hit(Card card) {
		setCount(card);
		this.playersHand.add(card);
	}

	public void addCard(Card card) {
		setCount(card);
		this.playersHand.add(card);
	}

	public void surrender() {
		this.playersHand.clear();
		this.bank = this.bet/2 + this.bank;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(Card card) {
		this.count =  this.count + card.getNumericValue();	
	}

	public void getHand() {
		for(Card c: this.playersHand) {
			System.out.println(c.toString());
		}
	}

	public void checkForAce() {
		for(Card c: this.playersHand) {
			if((c.getValue().compareTo("A")) == 0) {
				
			}
		}
	}
	
	public void Push() {
		System.out.println("Push");
		this.bank = this.bank + this.bet;
	}
	
	public void addToBank (double multi) {
		this.bank = this.bank + ((this.bet * multi) + this.bet);
	}
	
	public double getBet() {
		return this.bet;
	}

}

