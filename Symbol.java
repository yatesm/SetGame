package com.energyhub.interview.setgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Symbol {
	SQUIGGLE,
	DIAMOND,
	OVAL;
	
	private static final List<Symbol> values = new ArrayList<Symbol>(Arrays.asList(values()));
	private static final Random rand = new Random();
	/**
	 * Method to get a random value from the enum
	 *
	 * @return a random value from the enum
	 */
	public static Symbol getRandomSymbol() {
		return values.get(rand.nextInt(values.size()));
	}
}
