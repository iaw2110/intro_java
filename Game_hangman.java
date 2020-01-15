/* Ivan Wolansky
 * iaw2110
   Game */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.util.Collection;

public class Game {

	// this an arrayList of the current dictionary
	private ArrayList<String> currentList; 
	// this is an arrayList of all the letters guessed by the user
	private ArrayList<Character> guessedLetters;
	// this HashMap is used for creation of wordFamilies
	private HashMap<String, ArrayList<String>> wordFamilies;

	public Game() {

		currentList = new ArrayList<>();
		guessedLetters = new ArrayList<>();
		wordFamilies = new HashMap<>();
	}

	// this method plays the game
	public void play() throws IOException {

		System.out.println("Ready to play Evil Hangman? Enter 1 to play!");
		Scanner scan = new Scanner(System.in);
		int begin = scan.nextInt();

		// this while loop continues to play until the user decides against it
		while (begin == 1) {

			// opens and reads the dictionary file
			File dictionary = new File("/Users/iawol/Desktop/Comp Sci "
					+ "Assignments/iaw2110_project5/dictionary.txt");

			Scanner input = new Scanner(dictionary);

			// makes an arrayList of the entire dictionary to later be edited
			while (input.hasNext()) {
				currentList.add(input.next());
			}

			System.out.println("Enter the number of letters in the word " 
					+ "you wish to guess.");

			// takes the user input for word length
			Scanner in = new Scanner(System.in);
			int wordLength = in.nextInt();

			// temporary dictionary used to make new currentList of words
			ArrayList<String> tempDict = new ArrayList<>();

			// runs the new dictionary formation
			boolean again = true;

			// creates a new dictionary based off of the word length
			// until again is false
			while (again == true) {
				for (int i = 0; i < currentList.size(); i++) {
					if (currentList.get(i).length() == wordLength) {
						tempDict.add(currentList.get(i));
					}
				}
				if (tempDict.size() == 0) {
					System.out.println("No word has that number of" 
							+ " letters!! Enter a new length.");
					wordLength = in.nextInt();
				} else {
					currentList = tempDict;
					again = false;
				}
			}

			// allows the user to get a number of the words remaining
			System.out.println("Would you like a running total of the number "
						+ "of words remaining? If yes, enter a 1.");
			int run = in.nextInt();
			if (run == 1) {
				System.out.println("There are currently " + currentList.size() 
						+ " words.");
				System.out.println();
			}
			
			// prompts the user to get enter the number of guesses 
			// he/she wants
			System.out.println("Enter the number of guesses you would like.");
			int guesses = in.nextInt();
			while (guesses < 1) {
				System.out.println("Don't enter a number less than 1!! " 
						+ "Enter a new number of guesses!!");
				Scanner s = new Scanner(System.in);
				guesses = s.nextInt();
			}

			// plays the game until the player runs out of guesses
			while (guesses > 0) {

				System.out.println("Guess a letter. No repeats!");
				Scanner b = new Scanner(System.in);
				char letter = b.next().charAt(0);
				
				// adds to 0th index so that no counter variable 
				// needed for letter position in arrayList
				guessedLetters.add(0, letter);

				boolean valid = true;

				// checks to see if the letter is valid
				while (valid == true) {
					// outer if statement
					// checks to see if there's a repeated letter
					if (guessedLetters.size() > 1) {
						for (int i = 0; i < guessedLetters.size() - 1; i++) {
							if (guessedLetters.get(0).equals(
									guessedLetters.get(i + 1))) {
								System.out.println("You already guessed " 
									+ "that!! Guess a new letter!!");
								letter = b.next().charAt(0);
								guessedLetters.remove(0);
								guessedLetters.add(0, letter);
								i = 0;
							// if they aren't equal, breaks out of loop
							} 
							else {
								valid = false;
							}
						}
					} 
					// if there's only one letter in the arrayList, it breaks
					// out of the loop
					else {
						valid = false;
					}
				}

				// constructs a HashMap with the current dictionary
				// to evaluate
				for (int i = 0; i < currentList.size(); i++) {
					String currentWord = currentList.get(i);
					// Construct WordFamily pattern
					char[] splited = currentWord.toCharArray();
					for (int j = 0; j < splited.length; j++) {
						boolean replace = true;
						for (int k = 0; k < guessedLetters.size(); k++) {
							if (splited[j] == guessedLetters.get(k)) {
								replace = false;
							}
						}
						if (replace) {
							splited[j] = '_';
						}
					}
					String pattern = new String(splited);
					if (wordFamilies.containsKey(pattern)) {
						ArrayList<String> saveFamily = wordFamilies.
								get(pattern);
						saveFamily.add(currentWord);
						wordFamilies.put(pattern, saveFamily);
					} 
					else {
						ArrayList<String> newFamily = new ArrayList<>();
						newFamily.add(currentWord);
						wordFamilies.put(pattern, newFamily);
					}
				}

				// finds the largest wordFamily
				// tempMax used to find the largest wordFamily
				Collection<ArrayList<String>> coll = wordFamilies.values();
				ArrayList<String> tempMax = new ArrayList<>();
				for (ArrayList<String> x : coll) {
					if (tempMax.size() < x.size()) {
						tempMax = x;
					}
				}
				// clear the HashMap so that memory does not get full
				wordFamilies.clear();
				currentList = tempMax;
				
				// if the guess is "wrong" then the player loses a guess
				// indexOf returns false when the first word of
				// currentList has none of the guessed letter
				if (currentList.get(0).indexOf(getLetter()) == -1) {
					System.out.println("That's a bad guess.");
					guesses--;
				}
				// temporary variable to print the pattern
				String printWord = currentList.get(0);
				
				// reconstruct WordFamily pattern of the currentList
				// to be printed for the user
				char[] splited = printWord.toCharArray();
				for (int j = 0; j < splited.length; j++) {
					boolean replace = true;
					for (int k = 0; k < guessedLetters.size(); k++) {
						if (splited[j] == guessedLetters.get(k)) {
							replace = false;
						}
					}
					if (replace) {
						splited[j] = '_';
					}
				}
				String pattern = new String(splited);
				
				// breaks out of the game while loop once the word is guessed
				if (pattern.equals(currentList.get(0))) {
					guesses = -1;
				}
				// displays the guesses left
				if (guesses > 0) {
					System.out.println("You have " + guesses + 
							" guesses left.");
				}

				// displays the letters guessed
				System.out.println("The letters you have guessed so far: " 
						+ guessedLetters);

				// displays the word pattern
				System.out.println("The current pattern is " + pattern);

				// displays the number of words remaining
				if (run == 1) {
					System.out.println("There are/is " + currentList.size() 
						+ " word(s) remaining.");
				}
			}
			// when guesses = -1, the game stops
			if (guesses == -1) {
				System.out.println("Wow! You actually won!!");
			}
			if (guesses == 0) {
				// computer just says that the word was the first word
				// in the currentList
				System.out.println("The word was " + currentList.get(0));
				System.out.println();
				System.out.println("Would you like the remaining words to "
						+ "be printed to a text file? If the answer is " 
							+ "yes, enter 1.");
				Scanner rem = new Scanner(System.in);
				int print = rem.nextInt();
				
				if (print == 1) {
					try {

						PrintWriter writer = new PrintWriter
								("../remaining.txt");
						for (int i = 0; i < currentList.size(); i++) {
							writer.println(currentList.get(i));
						}
						writer.close();
					} catch (IOException e) {
						System.out.println("File wasn't found...");

					}
				}
			}

			System.out.println("Would you like to play again? "
					+ "If yes, enter 1.");
			guessedLetters.clear();
			begin = scan.nextInt();
		}
	}

	// returns the first letter in the arrayList which is always 
	// the last letter guessed by the player
	public Character getLetter() {
		return guessedLetters.get(0);
	}
}