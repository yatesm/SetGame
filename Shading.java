package com.energyhub.interview.setgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Shading enum.  Contains information regarding the shading of the symbol on a card.
 */
public enum Shading {
	SOLID,
	STRIPED,
	OUTLINED;
	
	private static final List<Shading> values = new ArrayList<Shading>(Arrays.asList(values()));
	private static final Random rand = new Random();

	/**
	 * @return A randomly selected shading from the enum
     */
	public static Shading getRandomShading() {
		return values.get(rand.nextInt(values.size()));
	}
}
