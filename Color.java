package com.energyhub.interview.setgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Color {
	RED, 
	GREEN, 
	PURPLE;
	
	private static final List<Color> values = new ArrayList<Color>(Arrays.asList(values()));
	private static final Random rand = new Random();

	/**
	 * Method to get a random value from the enum
	 *
	 * @return a random value from the enum
     */
	public static Color getRandomColor() {
		return values.get(rand.nextInt(values.size()));
	}
}


