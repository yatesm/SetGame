package com.energyhub.interview.setgame;

import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String [] args) {
		char answer;
		int cardNum, cardSetNum;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Welcome to the Set Game");


			do {
				System.out.println("How many cards would you like to play with?");
				cardNum = scanner.nextInt();
				if (cardNum < 0)
					System.out.println("Invalid number of cards, please enter an number greater than 0.");
			} while (cardNum <= 0);


			do {
				System.out.println("How many cards are required to form a set?");
				cardSetNum = scanner.nextInt();
				if (cardSetNum <= 0 || cardSetNum > cardNum)
					System.out.println("The number of cards required to form a set must be greater than zero and less " +
							"than or equal to the number of cards in play");
			} while (cardSetNum <= 0 || cardSetNum > cardNum);

			final long startTime = System.currentTimeMillis();


			PileOfCards pile = new PileOfCards(cardNum);
			final long powerSetStartTime = System.currentTimeMillis();
			List<Hand> hands = pile.getHands(cardSetNum);
			final long powerSetEndTime = System.currentTimeMillis();

			int numSets = 0;
			for (Hand hand : hands)
				if (hand.isSet()) {
					numSets++;
					System.out.println("Found a set:");
					System.out.println(hand + "\n");
				}

			System.out.println("Found " + numSets + " sets from " + hands.size() + " possible sets.");

			final long endTime = System.currentTimeMillis();

			System.out.println("Total runtime: " + (endTime - startTime) / 1000 + " seconds.");
			System.out.println("Time to generate power set: " + (powerSetEndTime - powerSetStartTime) / 1000 + " seconds");

			do {
				System.out.println("Would you like to play again?  y/n");
				answer = scanner.next().charAt(0);
			} while (answer != 'y' && answer != 'n');
		} while(answer == 'y');
	}
}
