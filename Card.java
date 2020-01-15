/* Ivan Wolansky
 * iaw2110
   Card */

public class Card implements Comparable<Card> {

	// public instance variables so that the Game class can access them
	public int suit; // uses integers 1-4 to encode the suit
	public int rank; // uses integers 1-13 to encode the rank

	public Card(int s, int r) {
		// make a card with suit s and rank r
		
		this.suit = s;
		this.rank = r;
	}

	public int compareTo(Card c) {
		// use this method to compare cards so they may be easily sorted
		// first compares by rank, and if they are the same, compares by suit
		
		int answer = 0;

		if (this.rank < c.rank) {
			answer = -1; 
		}
		if (this.rank > c.rank) {
			answer = 1;
		}
		
		if (this.rank == c.rank) {
			if (this.suit < c.suit) {
				answer = -1; 
			}
			if (this.suit > c.suit) {
				answer = 1;
			}
			if (this.suit == c.suit) {
				answer = 0;
			}
		}
		return answer;
	}

	public String toString() {
		// use this method to easily print a Card object
		
		String cardRank = "rank";
		String cardSuit = "suit";
		
		if (suit == 1) {
			cardSuit = "Clubs";
		}
		else if (suit == 2) {
			cardSuit = "Diamonds";
		}
		else if (suit == 3) {
			cardSuit = "Hearts";
		}
		else if (suit == 4) {
			cardSuit = "Spades";
		}
		if (rank == 1) {
			cardRank = "Ace";
		}
		else if (rank == 11) {
			cardRank = "Jack";
		}
		else if (rank == 12) {
			cardRank = "Queen";
		}
		else if (rank == 13) {
			cardRank = "King";
		} else {
			cardRank = Integer.toString(rank);
		}
		return cardRank + " of " + cardSuit;
	}
	
	public int getRank() {
		// gets the card's rank for the game class
		return rank;
	}
	
	public int getSuit() {
		// gets the card's suit for the game class
		return suit;
	}
}
