package com.energyhub.interview.setgame;

import java.util.*;

/**
 * Card class.  Encodes a single card containing N descriptors.
 */
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
	 * toString() function.  Produces pleasant-on-the-eyes (barring lack of formatting) string-representation of the
	 * card.
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
		return s.trim();
	}

	/**
	 * Returns the value of a passed descriptor string for the calling object.
	 * @param descriptor String value in the descriptorMap corresponding to the passed string key.  Null otherwise.
	 * @return the value of descriptor on the calling card.
     */
	public String getDescriptor(String descriptor) {
		return descriptorMap.get(descriptor);
	}

	/**
	 * Compares two cards together.
	 *
	 * @param c the card the calling object is being compared to.
	 * @return 0 if the cards are the same, 1 if the cards differ in any descriptor or the number of descriptors
     */
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

	/**
	 * Returns the set of keys corresponding to objects in the descriptor map.
	 *
	 * @return Set interface object containing all keys in the descriptorMap
     */
	public Set<String> getDescriptorKeySet() {
		return descriptorMap.keySet();
	}
}
