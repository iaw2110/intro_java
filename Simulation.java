/* Ivan Wolansky
 * iaw2110
   Simulation */

public class Simulation {
	
	public static void main(String[] args) {
		final double TRIALS = 1e6; // game always played a million times
		int wins = 0;
		int losses = 0;
		Die n = new Die(6);
		for (int i = 0; i < TRIALS; i++) {
			int temp = 0; // default temp = 0
			for (int k = 0; k < 4; k++) { // game, 4 rolls max per game
				n.roll(); 
				if (n.getSide() == 6) {
					temp++;
					break;
				}
			}
			if(temp == 0) // when temp is 0, there is a loss
				losses++;
			else
				wins++; // when a 6 is rolled, temp is 1 so there is a win
		}
		System.out.println("The gambler would make $" + 
		(wins - losses) + "in game 1.");
		// End game 1, beginning game 2
		wins = 0;
		losses = 0;
		Die m = new Die(6);
		for (int j = 0; j < TRIALS; j++) {
			int temp = 0; // default temp = 0
			for (int k = 0; k < 4; k++) {
				n.roll();
				m.roll();
				if (n.getSide() == 6 && m.getSide() == 6) { /* compensates for
												both dice needing a 6 to win */
					temp++;
					break;
				}
			}
			if(temp == 0)
				losses++;
			else
				wins++;
			
		}
		System.out.println("The gambler would make $" + 
		(wins - losses) + "in game 2.");
	}

} // end class