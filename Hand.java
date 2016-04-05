package com.energyhub.interview.setgame;

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
		
		if(isSymbolSet() && isShadingSet() && isNumberSet())
			returnValue = true;
		
		return returnValue;
	}

	public boolean isSymbolSet() {
		boolean returnValue = true;
		int numEquals = 0;
		int numNotEquals = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			for(int j = 0; j < this.hand.size(); j++) {
				if(i != j) {
					if(hand.get(i).getSymbol() == hand.get(j).getSymbol())
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
	
	public boolean isShadingSet() {
		boolean returnValue = true;
		int numEquals = 0;
		int numNotEquals = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			for(int j = 0; j < this.hand.size(); j++) {
				if(i != j) {
					if(hand.get(i).getShading() == hand.get(j).getShading())
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
	
	public boolean isNumberSet() {
		boolean returnValue = true;
		int numEquals = 0;
		int numNotEquals = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			for(int j = 0; j < this.hand.size(); j++) {
				if(i != j) {
					if(hand.get(i).getNumber() == hand.get(j).getNumber())
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
	
	public boolean isColorSet() {
		boolean returnValue = true;
		int numEquals = 0;
		int numNotEquals = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			for(int j = 0; j < this.hand.size(); j++) {
				if(i != j) {
					if(hand.get(i).getColor() == hand.get(j).getColor())
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
	
}
