package com.energyhub.interview.setgame;

import java.util.*;

public class Card implements Comparable<Card> {
	private final Map<String, String> descriptorMap;

	/**
	 * Generates a card given the color, shading, symbol and number on the card.
	 *
	 */
	public Card(Map<String, String> descriptorMap) {
		this.descriptorMap = new HashMap<String, String>();
		this.descriptorMap.putAll(descriptorMap);
	}

	/**
	 * toString() function.  Produces pleasant-on-the-eyes (barring caps) string-representation of the card.
	 *
	 * @return string representation of the card
     */
	@Override
	public String toString() {
		List<String> descriptorList = new ArrayList<String>();
		descriptorList.addAll(descriptorMap.keySet());
		Collections.sort(descriptorList);
		String s = "";
		for(String descriptor : descriptorList) {
			s += descriptorMap.get(descriptor) + " ";
		}
		return s;
	}

	public String getDescriptor(String descriptor) {
		return descriptorMap.get(descriptor);
	}

	public int compareTo(Card c) {
		int returnValue = 0;
		List<String> descriptorList = new ArrayList<String>();
		descriptorList.addAll(descriptorMap.keySet());
		Collections.sort(descriptorList);
		for(String descriptor : descriptorList) {
			if(this.descriptorMap.get(descriptor).compareTo(c.descriptorMap.get(descriptor)) != 0) {
				returnValue = -1;
				break;
			}
		}
		return returnValue;
	}
}
