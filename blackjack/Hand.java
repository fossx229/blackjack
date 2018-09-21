import java.util.ArrayList;

public class Hand {
	public ArrayList<Card> hand;
	private int count = 0;
	private int aceCount = 0;
	
	public Hand() {
		this.hand = new ArrayList<Card>();
	}

	public void addToHand(Card card) {
		this.hand.add(card);
		this.setCount(card);
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(Card card) {
		this.count =  this.count + card.getNumericValue();	
	}
	
	
	public void printHand() {
		for(Card c: this.hand) {
			System.out.println(c.toString());
		}
	}
	
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
	
	public void resetAceValue() {
		for(Card c: this.hand) {
			if((c.getValue().compareTo("A")) == 0) {
				c.setValue(11);
				this.count = this.count +10;
				this.aceCount = this.aceCount + 1;
			}
		}
	}
	
	public int getAceCount() {
		return this.aceCount;
	}
	
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
	
	public void printCard() {
		for(Card c: this.hand) {
			System.out.println(c.toString());
			break;
		}
	}
	
	public void clear() {
		this.hand.clear();
		this.count = 0;
		this.aceCount = 0;
	}

}
