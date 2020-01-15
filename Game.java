/* Ivan Wolansky
 * iaw2110
   Game */

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private Player p;
	private Deck cards;
	private int payout;
	private ArrayList<Card> playerHand;
	
	public Game(String[] testHand) {
		// helps user test a hand
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace - king
		// s1 = ace of spades
		// testhand = s1 s13 s12 s11 s10 = royal flush
		
		p = new Player();
		playerHand = new ArrayList<Card>();
		
		for (int i = 0; i < 5; i++) {
			int testSuit = 0;
			int testRank = 0;
			String temp = testHand[i];
			
			// figures out what suit the test card has
			if (temp.contains("c")) {
				testSuit = 1;
			}
			else if (temp.contains("d")) {
				testSuit = 2;
			}
			else if (temp.contains("h")) {
				testSuit = 3;
			}
			else if (temp.contains("s")) {
				testSuit = 4;
			}
			
			// figures out the test card's rank
			testRank = Integer.parseInt(temp.substring(1));
			
			// creates a new card and adds it to the player's test hand
			Card testCard = new Card(testSuit, testRank);
			playerHand.add(testCard);
		}
	}

	public Game() {
		// This no-argument constructor is to actually play a normal game
		
		p = new Player();
	}

	public void play() {
		// this method plays the game

		Scanner input = new Scanner(System.in);
		cards = new Deck();
		cards.shuffle();
		
		// allows the user to play or test
		System.out.println("Enter 1 to play Video Poker, enter 2 to test!");
		int begin = input.nextInt();
		boolean on = false;
		
		// when begin == 1 the game will be played, otherwise it tests a 
		// player inputed hand
		if (begin == 1) {
			on = true;
			
		// only plays the game while on == true
			while (on) {
			
				System.out.println("How many tokens between 1 and 5 are you "
						+ "betting?");
				
				double tokens = input.nextDouble();
			
				p.bets(tokens);
				for (int i = 0; i < 5; i++) {
					// adds the cards to the player's hand
					p.addCard(cards.deal()); 
				}
				
				// sorts the hand for the player
				p.sortHand(); 
				System.out.println("This is your hand."); 
				
				// displays player's hand
				p.printHand(); 
		
				System.out.println("Enter the position of the card(s) you want"
						+ " to exchange (0 is the first card and 4 is the last"
						+ " card) separated by a space. If there are none then"
						+ " enter no");
	
				// the player determines which card to take out
				input.nextLine();
				
				String numbers = input.nextLine();
				if (numbers != "no") {
				String[] positions = numbers.split(" ");
					for (int i = positions.length - 1; i >= 0 ; i--) {
						int num = Integer.parseInt(positions[i]);
						p.removeCard(p.takeCard(num));
						p.addCard(cards.deal());		
					}
				}
				
				// sorts the hand for easy evaluation in the
				// findDuplicates helper method for checkHand
				p.sortHand(); 
				
				System.out.println("This is your new hand.");
				p.printHand();
		
				// checks the strength of the hand
				String conclusion = checkHand(p.getHand());
				System.out.println("You ended up with a " + conclusion);
				p.winnings(payout);
				System.out.println("You now have " + p.getBankroll() +
						  " in your bankroll.");
		
				System.out.println("If you wish to continue then "
						+ "enter 1.");
				
				// continues the game if the player chooses to
				int value = input.nextInt();
				if (value == 1)
				{
					p.freshHand(); 
					p.getBankroll();
				}
				else
				{
					on = false;
				}
			}
			input.close();
		}
		// this is for testing
		else {
			String outcome = checkHand(playerHand);
			System.out.println(outcome);
		}
	}

	public String checkHand(ArrayList<Card> hand) {
		// this method takes an ArrayList of cards
		// as input and then determine what evaluates it to and
		// returns that as a String
		
		String outcome = "";
		
		if (royalFlush(hand)) {
			outcome = "Royal Flush";
			payout = 250;
		} 
		else if (straightFlush(hand)) {
			outcome = "Straight Flush";
			payout = 50;
		} 
		else if (fourOfAKind(hand)) {
			outcome = "Four of a Kind";
			payout = 25;
		} 
		else if (fullHouse(hand)) {
			outcome = "Full House";
			payout = 6;
		} 
		else if (flush(hand)) {
			outcome = "Flush";
			payout = 5;
		} 
		else if (straight(hand)) {
			outcome = "Straight";
			payout = 4;
		} 
		else if (threeOfAKind(hand)) {
			outcome = "Three of a Kind";
			payout = 3;
		} 
		else if (twoPair(hand)) {
			outcome = "Two Pair";
			payout = 2;
		} 
		else if (pair(hand)) {
			outcome = "Pair";
			payout = 1;
		}
		else {
			outcome = "High Card";
			payout = 0;
		}
		return outcome;
	}
	
	// every method below is a helper method for the checkHand method
	private boolean royalFlush(ArrayList<Card> cards) {
		// checks for a royal flush using flush and straight
		// and specifics for a royal flush
		
		boolean royalFlush = false;
		if (flush(cards) && straight(cards) && cards.get(0).getRank() == 1 && 
		cards.get(4).getRank() == 13) {
			royalFlush = true;
		}
		return royalFlush;
	}

	private boolean straightFlush(ArrayList<Card> cards) {
		// checks for a straight flush using flush and straight
		
		boolean straightFlush  = false;
		if (flush(cards) && straight(cards)) {
			straightFlush = true;
		}
		return straightFlush;
	}

	private boolean fourOfAKind(ArrayList<Card> cards) {
		// checks for a four of a kind using findDuplicates
		
		return findDuplicates(cards, 4, 1);
	}

	private boolean fullHouse(ArrayList<Card> cards) {
		// checks for a Full House using findDuplicates		
		
		return findDuplicates(cards, 2, 3) || findDuplicates(cards, 3, 2);
	}

	private boolean flush(ArrayList<Card> cards) { 
		// checks for a Flush
		
		boolean flush = true;
		
		for (int i = 0; i < 4; i++) {
			if (cards.get(i).suit != cards.get(i + 1).suit) {
				flush = false;
			}
		}
		return flush;
	}

	private boolean straight(ArrayList<Card> cards) {
		// checks for a Straight
		
		boolean straight = false;
		int counter = 1;
		
		for (int i = 1; i < 5; i++) {
			if (cards.get(i).rank == cards.get(i - 1).rank + 1) {
				counter++;
				if (counter == 5) {
					straight = true;
				}
			}
			else if (cards.get(i - 1).rank == 1 && cards.get(i).rank == 10) {
				for (int j = 2; j < 5; j++) {
					if (cards.get(j).rank == cards.get(j - 1).rank + 1) {
						straight = true;
					}	
				}
			}
		}
		return straight;
	}

	private boolean threeOfAKind(ArrayList<Card> cards) {
		// checks for a Three of a Kind using findDuplicates
		
		return findDuplicates(cards, 3, 1) || findDuplicates(cards, 1, 3);
	}

	private boolean twoPair(ArrayList<Card> cards) {
		// checks for a Two Pair using findDuplicates
		
		return findDuplicates(cards, 2, 2);
	}
	
	private boolean pair(ArrayList<Card> cards) {
		// checks for a Pair using findDuplicates
		
		return findDuplicates(cards, 2, 1) || findDuplicates(cards, 1, 2);
	}
	
	// helper method for Pair, Two Pair, Three of a Kind, Full House
	// and Four of a Kind that checks the number of duplicate elements in hand
	private boolean findDuplicates(ArrayList<Card> cards, int x, int y) { 
		
		int occur1 = 1;
		int occur2 = 1;
		int i = 0;
		int j = 1;
		
		while (i < cards.size()) {
			int count = 0;
			while (j < cards.size() && cards.get(i).rank 
					== cards.get(j).rank) {
				count++;
				j++;
			}
			i += + 1 + count;
			j = i + 1;
			if (count != 0 && occur1 == 1) {
				occur1 += count;
				count = 0;
			}
			else if (count != 0) {
				occur2 += count;
				count = 0;
			}
		
		}
		return x == occur1 && y == occur2;
	}
}