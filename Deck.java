/* Ivan Wolansky
 * iaw2110
   Deck */

import java.util.Arrays;
import java.util.Collections;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	public Deck() {
		// constructs a new deck for the game class
		
		top = 0;
		cards = new Card[52];

		int numberOfCards = 0;
		for (int suit = 1; suit < 5; suit++) {
			for (int rank = 1; rank < 14; rank++) {
				cards[numberOfCards] = new Card(suit, rank);
				numberOfCards++;
			}
		}
	}

	public void shuffle() {
		// shuffle the deck here
		Collections.shuffle(Arrays.asList(cards));
	}

	public Card deal() {
		// deal the top card in the deck

		Card topCard = cards[top];
		top++;
		return topCard;
	}
}
