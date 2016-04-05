package com.energyhub.interview.setgame;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Hand {
	private final List<Card> hand;
	
	
	/**
	 *
	 * Initializes empty hand
	 */
	public Hand() {
		hand = new ArrayList<Card>(3);
	}
	
	/**
	 * 
	 * @param numCards
	 * @return card corresponding to the cardNumber position in hand.
	 * 		   Null if cardNumber under/over flows the number of cards in hand.
	 */
	public Hand(int numCards) {
		hand = new ArrayList<Card>(numCards);
		for(int i = 0; i < numCards; i++)
			hand.add(CardFactory.drawCard());
	}
	
	public Card getCard(int cardNumber) {
		Card retValue = null;
		if(cardNumber >= 0 && cardNumber < this.hand.size())
			retValue = hand.get(cardNumber);
		return retValue;
	}
	
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
	
	public boolean isSet() {
		boolean returnValue = false;

		String [] methodNames = {"getSymbol", "getShading", "getNumber", "getColor"};
		boolean isSet = true;
		for(int i = 0; i < methodNames.length && isSet; i++) {
			isSet = isSet && determineIsSet(methodNames[i]);
		}
		return isSet;
	}

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
				break;
			} else if(numEquals > 0 && numNotEquals == 0) {
				//Implies everything is the same
				//Implies is a set
				returnValue = true;
				break;
			}
		} //End outer for loop

		return returnValue;
	}

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
	
}
