
import java.util.ArrayList;

/**
 * 
 * This class represents a card
 *
 */
public class Card implements Comparable<Card>{

	final private String suit;
	final private String value;
	private int numericValue = 0;
	final static private String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	final static private String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

	/**
	 * Card constructor
	 * @param suit
	 * @param value
	 */
	public Card(String suit, String value){
		this.suit = suit;
		this.value = value;
		for (int i = 0; i < 13; i++) {
			if (values[i].equals(value)) {
				numericValue = i + 2;			
			}
		}
	}


	public static Deck getDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		for (String suit: suits) {
			for (String value: values) {
				deck.add(new Card(suit, value));
			}
		}
		return new Deck(deck);
	}

	/**
	 * getter for suit
	 * @return suit
	 */
	public String getSuit(){
		return suit;
	}

	/**
	 * getter for value
	 * @return value
	 */
	public String getValue(){
		return value;
	}

	/**
	 * getter for numericValue
	 * @return numericValue
	 */
	public int getNumericValue(){
		return numericValue;
	}

	/**
	 * override toString() method
	 */
	public String toString(){
		return value + " of " + suit;
	}

	@Override
	/**
	 * Compare the numeric values of this Card and the parameter Card
	 * @param that
	 * @return an int value
	 */
	public int compareTo(Card that){
		String suit1 = this.getSuit();
		String suit2 = that.getSuit();

		int suitValue1 = 0;
		int suitValue2 = 0;

		for(int i = 0; i < suits.length; i ++){
			if(suit1 == suits[i]) break;
			else suitValue1++;
		}

		for(int i = 0; i < suits.length; i ++){
			if(suit2 == suits[i]) break;
			else suitValue2++;
		}

		if(suitValue1 == suitValue2) return this.getNumericValue() - that.getNumericValue();
		else return suitValue1 - suitValue2;

	}
}