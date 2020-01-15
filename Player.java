/* Ivan Wolansky
 * iaw2110
   Player */

import java.util.ArrayList;
import java.util.Collections;

public class Player {

	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
	private double bet;

	public Player() {
		// create a player here
		bankroll = 0;
		bet = 0;
		hand = new ArrayList<>();
		hand.ensureCapacity(5);
	}

	public void addCard(Card c) {
		// add card c to the player's hand
		hand.add(c);
	}

	public void removeCard(Card c) { 
		// remove the card c from the player's hand
		hand.remove(c);
	}

	public void bets(double amt) {
		// player makes a bet
		bet = amt;
		bankroll -= bet;
	}

	public void winnings(double odds) {
		// adjust bankroll if player wins
		bankroll += bet * odds;
	}

	public double getBankroll() {
		// return current balance of bankroll
		return bankroll;
	}

	public void printHand() { 
		// prints the player's hand
		for (int i = 0; i < 5; i++) {
			System.out.println(hand.get(i));
		}
	}
	
	public ArrayList<Card> getHand() { 
		// gets the hand from player
		return hand;
	}
	
	public Card takeCard(int take) {
		// method that takes a card from the player's hand
		return hand.get(take);
	}
	
	public void freshHand() { 
		// creates a fresh hand for a new game
		for (int i = hand.size() - 1; i >= 0 ; i--) { 
			hand.remove(takeCard(i));
		}
	}
	public void sortHand() {
		// sorts the player's hand in ascending order
		Collections.sort(hand);
	}
}