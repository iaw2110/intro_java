/* Ivan Wolansky
 * iaw2110
   Evil Hangman Tester */

import java.io.IOException;
import java.util.Scanner;

public class HangmanTester {

	public static void main(String[] args) {

		boolean go = true;
		while (go == true) {
			try {
				Game g = new Game();
				g.play();
			} 
			catch (IOException e) {
				System.out.println("Please try again with the correct input "
						+ "file name");
				Scanner scan = new Scanner(System.in);
				args[0] = scan.next();
				scan.close();
			} 
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Specify the command-line argument!!!!!");
				go = false;
			}
		}
	}
}
