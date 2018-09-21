
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a deck
 * 
 */
public class Deck implements Iterable<Card> {
	final static private String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	final static private String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * Deck constructor
	 * 
	 * @param cards
	 */
	public Deck() {
		for(int z = 0; z < 1; z++) {
			for (int i = 0; i < suits.length; i++) {
				for (int j = 0;  j < values.length-4; j++) {
					this.cards.add(new Card(suits[i], values[j], Integer.parseInt((values[j]))));
				}
			}
			for (int i = 0; i < suits.length; i++) {
				for (int j =  values.length-3; j <  values.length-1; j++) {
					this.cards.add(new Card(suits[i], values[j], 10));
				}
			}
			
			for (int i = 0; i < suits.length; i++) {
				for (int j = values.length-1; j < values.length; j++) {
					this.cards.add(new Card(suits[i], values[j], 1));
				}
			}
		}
	}

	/**
	 * @return true if the deck has at least one card, and false otherwise
	 */
	public boolean hasCards() {
		return (cards.size() > 0);
	}

	/**
	 * put a Card object on the bottom
	 * 
	 * @param card
	 */
	public void takeCard(Card card) {
		cards.add(0, card);
	}

	/**
	 * Removes the last element of deck and returns it. Also changes hasCards to
	 * false if deck is now empty
	 * 
	 * @return deck
	 */
	public Card playCard() {
		if(cards.size() == 0) {
			System.out.println("You can't play a card from an empty deck");
			System.exit(0);
		}
		return cards.remove(cards.size() - 1);
	}

	/**
	 * returns the size of the deck
	 * 
	 * @return deck.size()
	 */
	public int getSize() {
		return cards.size();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public ArrayList<Card> getCards(){
		return cards;
	}

	@Override
	public Iterator<Card> iterator() {
		return new CardIterator();
	}

	private class CardIterator implements Iterator<Card> {
		private int position = 0;
		private Card card = null;

		public boolean hasNext() {
			return (position < getSize());
		}

		public Card next() {
			if (position < getSize()) {
				card = cards.get(position);
				position++;
				return card;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}