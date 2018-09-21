import java.util.ArrayList;

public class Hand {
	public ArrayList<Card> hand;
	private int count = 0;
	private int aceCount = 0;
	
	
	/**
	 * Hand constructor
	 */
	public Hand() {
		this.hand = new ArrayList<Card>();
	}

	/**
	 * @param card
	 * add card to a hand and updates the count of a hand
	 */
	public void addToHand(Card card) {
		this.hand.add(card);
		this.setCount(card);
	}
	
	/**
	 * getter for count
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * setter for count
	 */
	public void setCount(Card card) {
		this.count =  this.count + card.getNumericValue();	
	}
	
	/**
	 * prints hand
	 */
	public void printHand() {
		for(Card c: this.hand) {
			System.out.println(c.toString());
		}
	}
	
	/**
	 * checks for ace and sets it value to 11
	 */
	public void checkForAce() {
		for(Card c: this.hand) {
			if((c.getValue().compareTo("A")) == 0) {
				c.setValue(11);
				this.count = this.count +10;
				this.aceCount = this.aceCount + 1;
				break;
			}
		}
		
	}
	
	
	/**
	 * getter for the ace count
	 */
	public int getAceCount() {
		return this.aceCount;
	}
	
	/**
	 * checks for more than one ace and resets a ace that counts as 11 as 1
	 */
	public void resetAce( ) {
		for(Card c: this.hand) {
			if((c.getValue().compareTo("A")) == 0) {
				c.setValue(1);
				this.count = this.count +10;
				this.aceCount = this.aceCount + 1;
				break;
			}
		}
	}
	
	/**
	 * prints a card
	 */
	public void printCard() {
		for(Card c: this.hand) {
			System.out.println(c.toString());
			break;
		}
	}
	
	/**
	 * resets hand
	 */
	public void clear() {
		this.hand.clear();
		this.count = 0;
		this.aceCount = 0;
	}

}
