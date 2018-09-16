
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* This class represents a deck
* 
*/
public class Deck implements Iterable<Card> {

	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * Deck constructor
	 * 
	 * @param cards
	 */
	public Deck(ArrayList<Card> cards) {
		for (Card c : cards) {
			this.cards.add(c);
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