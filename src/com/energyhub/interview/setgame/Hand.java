package com.energyhub.interview.setgame;

import java.util.*;

/**
 * Hand class.  Contains data structures to support the aggregation of multiple cards and the logic behind determining
 * if N cards (a hand) represents a set.
 */
public class Hand implements Comparable<Hand> {
	private final List<Card> hand;
	private final Set<String> descriptorKeySet;
	
	/**
	 *
	 * Initializes empty hand
	 */
	public Hand() {
		descriptorKeySet = new HashSet<String>();
		hand = new ArrayList<Card>();
	}
	
	/**
	 * Constructs Hand object with numCards randomly generated Cards in its ArrayList
	 * Requires
	 *
	 * @param numCards number of cards to populate hand with.
	 */
	public Hand(int numCards, Set<String> descriptorKeySet) {
		this.descriptorKeySet = descriptorKeySet;
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
		//Add the card to the hand
		hand.add(card);
		//Add the descriptors from the card to the key set.  Necessary for ensuring the hand knows what descriptors to
		//compare
		descriptorKeySet.addAll(card.getDescriptorKeySet());
	}

	/**
	 * Overridden toString() method.  Returns a reasonably readable output of a Card object
	 * @return String representation of Card object.
     */
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
		boolean isSet = true;
		for(String descriptorKey : this.descriptorKeySet) {
			isSet = isSet && determineIsSet(descriptorKey);
		}
		return isSet;
	}

	/**
	 * Determines whether the given hand forms a set for the descriptor provided in descriptorKey.  Utilizes class-metadata
	 * and reflection to call the getter (to access a descriptor) described by descriptorKey.
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
	 * @param descriptorKey the name of the method to get the descriptor currently being checked.
	 * @return returns whether or not the hand encodes a set for the descriptor described by descriptorKey
     */

	public boolean determineIsSet(String descriptorKey) {
		boolean returnValue = true;
		int numEquals = 0;
		int numNotEquals = 0;
		//Outer loop over each card
		for(int i = 0; i < this.hand.size() && returnValue; i++) {
			//Inner loop over each card
			for(int j = 0; j < this.hand.size(); j++) {

				//Avoid comparing a card to itself.
				if(i != j) {
					if(hand.get(i).getDescriptor(descriptorKey).compareTo(hand.get(j).getDescriptor(descriptorKey)) == 0)
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
	 * Comparable interface method implementation.
	 * @param h the hand the calling object is compared to.
	 * @return Returns 0 if all cards in the calling object are contained in the
	 * passed Hand parameter, h.  Returns 1 otherwise, or if two hands are different sized.
     */
	public int compareTo(Hand h) {
		int returnValue = 0;
		if(h.size() != this.size())
			returnValue = -1;
		for(int i = 0; i < h.size() && returnValue == 0; i++) {
			if(!this.hand.contains((h.getCard(i)))) {
				returnValue = -1;
			}
		}
		return returnValue;
	}
	
}
