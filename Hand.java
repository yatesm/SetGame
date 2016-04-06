package com.energyhub.interview.setgame;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Hand implements Comparable<Hand> {
	private final List<Card> hand;
	
	
	/**
	 *
	 * Initializes empty hand
	 */
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	/**
	 * Constructs Hand object with containing numCards Cards in its ArrayList
	 *
	 * @param numCards number of cards to populate hand with.
	 */
	public Hand(int numCards) {
		hand = new ArrayList<Card>(numCards);
		for(int i = 0; i < numCards; i++)
			hand.add(CardFactory.drawCard());
	}

	/**
	 * Returns the number of cards in the hand ArrayList
	 *
	 * @return the size of the hand ArrayList
     */

	public int size() {
		return hand.size();
	}

	/**
	 * Returns the card in the Hand ArrayList at index cardNumber.  Returns null in the case of overflow or negative
	 * index.
	 *
	 * @param cardNumber the index of the card to be retrieved
	 * @return the card at index cardNumber or null if cardNumber > hand.size() or cardNumber < 0
     */
	
	public Card getCard(int cardNumber) {
		Card retValue = null;
		if(cardNumber >= 0 && cardNumber < this.hand.size())
			retValue = hand.get(cardNumber);
		return retValue;
	}

	/**
	 * Adds a card to the end of the hand ArrayList
	 * @param card card to be added to the hand ArrayList
     */

	public void addCard(Card card) {
		hand.add(card);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Card card : this.hand) {
			s = s + card.toString() + "\n";
		}
		return s;
	}

	/**
	 * Determines if the cards in the hand form a set.  A set is achieve, for each descriptor, every card in the hand
	 * has the same descriptor or all have different descriptors
	 *
	 * An interesting note is that the call to determineIsSet includes a methodName for a getter from the Card class.
	 * This is used to dynamically iterate through checking for being a set among the different descriptors.  This could
	 * be reimplemented dynamically, searching for functions of a particular naming type (say, get*), but is not
	 * required at this juncture.
	 *
	 * @return boolean value corresponding to whether or not the hand is a set.
     */

	public boolean isSet() {
		String [] methodNames = {"getSymbol", "getShading", "getNumber", "getColor"};
		boolean isSet = true;
		for(int i = 0; i < methodNames.length && isSet; i++) {
			isSet = isSet && determineIsSet(methodNames[i]);
		}
		return isSet;
	}

	/**
	 * Determines whether the given hand forms a set for the descriptor provided in methodName.  Utilizes class-metadata
	 * and reflection to call the getter (to access a descriptor) described by methodName.
	 *
	 * Algorithm is a roughly O(N^2) algorithm, as every card needs to be checked against every other card (excluding
	 * itself).  The algorithm has been optimized to exclude self-comparisons.  The algorithm has been optimized to
	 * conclude the cards do not form a set for the given descriptor in two ways:
	 *
	 * 1) If, in a given pass, checking card i against card 0...j, the number of cards with the same descriptor as card
	 * i and the number of cards with different descriptors as card i are both greater than 0, that implies that some
	 * cards have the same descriptor and some have different.  This implies that the hand does not form a set for the
	 * given descriptor.
	 *
	 * 2) If, in a given pass, checking card i against card 0...j, the number of cards with the same descriptor as card
	 * i is greater than 0, and the number of cards with different descriptors from card i is 0, that implies that
	 * all cards have the same descriptor.
	 *
	 * The call to the methodCaller method is used to dynamically determine which descriptor to use in checking for
	 * if a hand is a set.  The originally implementation was the same function, implemented three times, with the
	 * single if(hand.get(i).getDescriptor() == hand.get(j).getDescriptor() varying in each function.  While this method
	 * is a little harder to read, it is far less code to read and more maintainable.
	 *
	 * @param methodName the name of the method to get the descriptor currently being checked.
	 * @return returns whether or not the hand encodes a set for the descriptor described by methodName
     */

	public boolean determineIsSet(String methodName) {
		boolean returnValue = true;
		int numEquals = 0;
		int numNotEquals = 0;
		for(int i = 0; i < this.hand.size() && returnValue; i++) {
			for(int j = 0; j < this.hand.size(); j++) {
				if(i != j) {
					if(this.methodCaller(hand.get(i), methodName).equals(this.methodCaller(hand.get(j), methodName)))
						numEquals++;
					else
						numNotEquals++;
				}
			} //End inner for loop
			if(numEquals > 0 && numNotEquals > 0) {
				//Some things are equal, some are not
				//Implies that there is not a set.
				returnValue = false;
			} else if(numEquals > 0 && numNotEquals == 0) {
				//Implies everything is the same
				//Implies is a set
				returnValue = true;
				break;
			}
		} //End outer for loop

		return returnValue;
	}

	/**
	 * Terrible method that utilizes class metadata to invoke methods dynamically.  This function is one strategy for
	 * significantly reducing the number of duplicate lines/blocks of the code.  The functional difference between
	 * checking whether a hand is a set of one descriptor vs. another descriptor is the comparison between the values
	 * of that descriptor.
	 *
	 * By utilizing this method, we can pass the string, containing the methodName for the descriptor accessor, to the
	 * determineIsSet() method (from the isSet() method).
	 *
	 *
	 * @param card the card whose getter method will be invoked
	 * @param methodName the name of the method that will be invoked
     * @return the value returned by the function described by methodName
     */
	public Object methodCaller(Card card, String methodName) {
		Object returnValue = null;
		try {
			returnValue = card.getClass().getMethod(methodName).invoke(card);
		} catch (NoSuchMethodException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println("Tried to call a method that doesn't exist through reflection while determining isSet.");
			System.out.println("Exiting.");
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println("Tried to call a private method method via reflection.");
			System.out.println("Exiting.");
			System.exit(1);
		} catch (InvocationTargetException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println("Chained exception invoked by " + e.getCause());
			System.out.println("Exiting.");
			System.exit(1);
		}
		return returnValue;
	}

	public int compareTo(Hand h) {
		int returnValue = 0;
		if(h.size() != this.size())
			returnValue = -1;
		for(int i = 0; i < this.size() && returnValue == 0; i++) {
			if(this.getCard(i).compareTo(h.getCard(i)) != 0) {
				returnValue = -1;
			}
		}
		return returnValue;
	}
	
}
