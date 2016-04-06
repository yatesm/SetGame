package com.energyhub.interview.setgame;

import java.util.Random;

/**
 * CardFactory class
 * <p>
 *
 * Simple static-method based factory for creating cards.  Could be extended to encode an actual deck from which to
 * draw from.
 */
public class CardFactory {
	private static final int maxNumber = 3;
	private static final Random rand = new Random();
	
	public static Card drawCard() {
		return new Card(Color.getRandomColor(), Shading.getRandomShading(), 
				Symbol.getRandomSymbol(), rand.nextInt(maxNumber) + 1);
	}
	
}
