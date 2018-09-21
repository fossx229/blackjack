import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class UnitTests {
	private Game newGame = new Game();
	@Before 
	public void setUp() {
		newGame.testing = true;
		newGame.player = new Player(1000, "bob");
		newGame.player.bet = 100;
		Dealer dealer = new Dealer();
		newGame.dealer= dealer;
	}
	@Test
	public void testPlayerBlackJack() {
		newGame.player.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.player.getHand().addToHand(new Card("Spades", "King", 10));
		newGame.checkBlackJack();
		assertTrue(newGame.player.blackJackNumber == 1);
	}
	
	@Test
	public void testPlayerHit() {
		newGame.player.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.player.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.player.getHand().addToHand(new Card("Diamond", "Five", 5));
		System.out.println(newGame.player.getHand().getCount());
		assertTrue(newGame.player.getHand().getCount() == 21);
	}
	
	@Test
	public void testDealerHit() {
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.dealer.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.dealer.getHand().addToHand(new Card("Diamond", "Five", 5));
		assertTrue(newGame.dealer.getHand().getCount() == 21);
	}
	
	@Test
	public void testDealerBlackJack() {
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.dealer.getHand().addToHand(new Card("Spades", "King", 10));
		newGame.checkBlackJack();
		assertTrue(newGame.dealer.blackJackNumber == 1);
	}
	
	@Test
	public void testPush() {
		newGame.player.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.player.getHand().addToHand(new Card("Spades", "King", 10));
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.dealer.getHand().addToHand(new Card("Spades", "King", 10));
		newGame.checkBlackJack();
		assertTrue(newGame.player.numberTies == 1);
	}
	
	@Test
	public void testPlayerBusts() {
		newGame.player.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.player.getHand().addToHand(new Card("Spades", "King", 10));
		newGame.player.getHand().addToHand(new Card("Spades", "Queen", 10));
		newGame.playerHitOrNot();
	
		assertTrue(newGame.player.loses == 1);
	}
	
	@Test
	public void testDealerBusts() {
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 11));
		newGame.dealer.getHand().addToHand(new Card("Spades", "King", 10));
		newGame.dealer.getHand().addToHand(new Card("Spades", "Queen", 10));
		newGame.dealerHitOrNot();
		assertTrue(newGame.dealer.loses == 1);
	}
	
	@Test
	public void testDealerHitOrNot() {
		
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 1));
		newGame.dealer.getHand().addToHand(new Card("Clubs", "Ace", 1));
		newGame.dealer.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.dealerHitOrNot();
		assertTrue(newGame.dealer.getHand().getCount() == 17);
	}
	
	@Test
	public void resolveGame() {
		
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 1));
		newGame.dealer.getHand().addToHand(new Card("Clubs", "Ace", 1));
		newGame.dealer.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.player.getHand().addToHand(new Card("Spades", "Ace", 1));
		newGame.player.getHand().addToHand(new Card("Clubs", "Ace", 1));
		newGame.player.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.resolveGame();
		newGame.resolveGame();
		assertTrue(newGame.player.numberTies == 2);
		newGame.dealer.getHand().addToHand(new Card("Diamonds", "Ace", 1));
		newGame.resolveGame();
		assertTrue(newGame.player.loses == 1 && newGame.dealer.wins == 1);
		newGame.player.getHand().addToHand(new Card("Hearts", "Two", 2));
		newGame.resolveGame();
		assertTrue(newGame.player.wins == 1 && newGame.dealer.loses == 1);
	}

	@Test
	public void testPlayHitOrNot() {
		newGame.dealer.getHand().addToHand(new Card("Spades", "Ace", 1));
		newGame.dealer.getHand().addToHand(new Card("Clubs", "Ace", 1));
		newGame.dealer.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.player.getHand().addToHand(new Card("Spades", "Ace", 1));
		newGame.player.getHand().addToHand(new Card("Clubs", "Ace", 1));
		newGame.player.getHand().addToHand(new Card("Spades", "Five", 5));
		newGame.resolveGame();
		newGame.resolveGame();
		assertTrue(newGame.player.numberTies == 2);
		newGame.dealer.getHand().addToHand(new Card("Diamonds", "Ace", 1));
		newGame.resolveGame();
		assertTrue(newGame.player.loses == 1 && newGame.dealer.wins == 1);
		newGame.player.getHand().addToHand(new Card("Hearts", "Two", 2));
		newGame.resolveGame();
		assertTrue(newGame.player.wins == 1 && newGame.dealer.loses == 1);
	}
	
	@Test
	public void testFileReaderAndWriter() {
		
		newGame.player.wins = 1;
		newGame.player.loses = 1;
		newGame.player.blackJackNumber = 1;
		newGame.player.numberTies = 1;
		
		newGame.dealer.wins = 1;
		newGame.dealer.loses = 1;
		newGame.dealer.blackJackNumber = 1;

		newGame.writeData();
		Dealer dealer2 = new Dealer();
		newGame.player = new Player(1000, "bob2");
		newGame.dealer = dealer2;
		newGame.player.readStats();
		newGame.dealer.readStats();
		newGame.player.setStats();
		newGame.dealer.setStats();
	
		assertTrue(newGame.player.wins == 1 && newGame.dealer.loses == 1);
		assertTrue(newGame.player.loses == 1 && newGame.dealer.wins == 1);
		assertTrue(newGame.player.blackJackNumber == 1 && newGame.dealer.blackJackNumber == 1);
		assertTrue(newGame.player.numberTies == 1);
		
		
	}
}