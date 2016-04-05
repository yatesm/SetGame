package com.energyhub.interview.setgame;

import java.util.Random;

public class CardFactory {
	private static final int maxNumber = 3;
	private static final Random rand = new Random();
	
	public static Card drawCard() {
		return new Card(Color.getRandomColor(), Shading.getRandomShading(), 
				Symbol.getRandomSymbol(), rand.nextInt(maxNumber) + 1);
	}
	
}
