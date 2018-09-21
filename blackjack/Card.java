
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
	 * @param numericValue
	 */
	public Card(String suit, String value, int numericValue){
		this.suit = suit;
		this.value = value;
		this.numericValue = numericValue;
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
	/**
	 * setter for numericValue
	 * 
	 */
	public void setValue(int newValue) {
		this.numericValue = newValue;
	}


	@Override
	public int compareTo(Card arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}