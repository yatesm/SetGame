package com.energyhub.interview.setgame;

import java.util.List;
import java.util.Scanner;

/**
 * Driver class for executing the Set game.
 *
 * Requests an input file containing the names of input files containing information about possible descriptors for
 * each card dealt.  Requests input from the user for how many cards will be dealt, and how many cards are required
 * to form a set.  Times the entire process, as well as the process of generating the powerset and pruning the powerset
 * down to subsets of N cards.  Outputs all N-card hands that represent a set, as well as the total number of sets
 * found out of the total number of possible sets.  Requests input from user on whether or not they would like to
 * play again.
 */
public class Driver {

	public static void main(String [] args) {
		char answer;
		int cardNum, cardSetNum;
		String inputFileName = "";
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the Set Game");
		System.out.println("Please enter an input file that contains the name of file contain descriptors");
		System.out.println("The input file should contain one file per line, which names a descriptor.");
		System.out.println("Each file in the input file should contain one descriptor per line");
		inputFileName = scanner.nextLine();
		CardFactory.initializeCardFactory(inputFileName);
		System.out.println("You entered " + inputFileName);

		//Game loop
		do {
			//Request input from user on how many cards to deal.
			do {
				System.out.println("How many cards would you like to play with?");
				cardNum = scanner.nextInt();
				if (cardNum < 0)
					System.out.println("Invalid number of cards, please enter an number greater than 0.");
			} while (cardNum <= 0);

			//Request input from user on how many cards are required to form a set.
			do {
				System.out.println("How many cards are required to form a set?");
				cardSetNum = scanner.nextInt();
				if (cardSetNum <= 0 || cardSetNum > cardNum)
					System.out.println("The number of cards required to form a set must be greater than zero and less " +
							"than or equal to the number of cards in play");
			} while (cardSetNum <= 0 || cardSetNum > cardNum);

			//Start the program timer
			final long startTime = System.currentTimeMillis();

			//Instantiate the pile of cards
			PileOfCards pile = new PileOfCards(cardNum);

			//Start power set timer
			final long powerSetStartTime = System.currentTimeMillis();
			//Generate power set
			List<Hand> hands = pile.getHands(cardSetNum);
			//Stop power set timer
			final long powerSetEndTime = System.currentTimeMillis();

			//Initialize set counter to 0
			int numSets = 0;
			//For each hand, determine if hand is a set.
			for (Hand hand : hands)
				//If hand is a set, increment set counter, output hand.
				if (hand.isSet()) {
					numSets++;
					System.out.println("Found a set:");
					System.out.println(hand + "\n");
				}

			//Output number of sets found.
			System.out.println("Found " + numSets + " sets from " + hands.size() + " possible sets.");

			//Stop the program timer
			final long endTime = System.currentTimeMillis();

			//Output program and power set time
			System.out.println("Total runtime: " + (endTime - startTime) / 1000 + " seconds.");
			System.out.println("Time to generate power set: " + (powerSetEndTime - powerSetStartTime) / 1000 + " seconds");

			//Query user to determine if they would like to play again.
			do {
				System.out.println("Would you like to play again?  y/n");
				answer = scanner.next().charAt(0);
			} while (answer != 'y' && answer != 'n');
		} while(answer == 'y');
	}
}
