package com.energyhub.interview.setgame;

import java.util.Scanner;

public class Driver {

	public static void main(String [] args) {
		char answer;
		int cardNum = 3;
		int iterations = 0;
		boolean isSet = false;
		Hand hand;
		Scanner scanner = new Scanner(System.in);
		do {
			iterations++;
			/*System.out.println("Welcome to the Set Game");
			System.out.println("How many cards would you like to play with?");
			
			do{
				cardNum = scanner.nextInt();
				if(cardNum < 0)
					System.out.println("Invalid number of cards, please enter an number greater than 0.");
			} while(cardNum <= 0);*/

			hand = new Hand(cardNum);
			System.out.println("Here's your hand: ");
			System.out.println(hand);

			if(hand.isSet()) {
				System.out.println("Hand is a set!");
				isSet = true;
			}
			else
				System.out.println("Hand is not a set!");
			/*do {
				System.out.println("Would you like to play again?  y/n");
				answer = scanner.next().charAt(0);
			} while(answer != 'y' && answer != 'n');*/

		} while(!isSet);
		System.out.println("found a set after " + iterations + " iterations");
		System.out.println(hand);
	}
}
