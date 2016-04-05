package com.energyhub.interview.setgame;

import java.util.Scanner;

public class Driver {

	public static void main(String [] args) {
		String answer;
		int cardNum;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Welcome to the Set Game");
			System.out.println("How many cards would you like to play with?");
			
			do{
				cardNum = scanner.nextInt();
				if(cardNum < 0)
					System.out.println("Invalid number of cards, please enter an number greater than 0.");
			} while(cardNum <= 0);
			
			Hand hand = new Hand(cardNum);
			System.out.println("Here's your hand: ");
			System.out.println(hand);
			if(hand.isSet())
				System.out.println("Hand is a set!");
			else
				System.out.println("Hand is not a set!");
			System.out.println("Would you like to play again?  y/n");
			scanner.reset();
			answer = scanner.nextLine();
		} while(answer.compareTo("y") == 0 || answer == "Y" || answer == "Yes" || answer == "yes");
	}
}
