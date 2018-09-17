import java.util.ArrayList;

public class Dealer {
	private int wins;
	private int loses;
	private int blackJackNumber;
	private int count;
	private ArrayList<Card> dealersHand = new ArrayList<Card>();
	private int numberOfDecks;
	private static Deck dealersDeck;

	public Dealer(int numberOfDecks) {
		this.dealersDeck = new Deck(numberOfDecks);
	}

	public void dealCards(ArrayList<Player> players) {
		for(int i=0; i<players.size(); i++) {
			for(int j=0; j<2; j++) {
			 players.get(i).addCard(dealersDeck.playCard());
			}
		}
		addCard(dealersDeck.playCard());
		addCard(dealersDeck.playCard());
	}
	
	public Card dealCard() {
		return dealersDeck.playCard();
	}
	
	public void addCard(Card card) {
		setCount(card);
		this.dealersHand.add(card);
	}
	
	public void setCount(Card card) {
		this.count =  this.count + card.getNumericValue();	
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void hit(Card card) {
		setCount(card);
		this.dealersHand.add(card);
	}
}
