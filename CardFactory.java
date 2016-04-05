package com.energyhub.interview.setgame;

import java.util.Random;

import com.energyhub.interview.setgame.descriptors.Color;
import com.energyhub.interview.setgame.descriptors.Shading;
import com.energyhub.interview.setgame.descriptors.Symbol;

class CardFactory {
	private static final Random rand = new Random();
	
	public static Card drawCard() {
		return new Card(Color.getRandomColor(), Shading.getRandomShading(), 
				Symbol.getRandomSymbol(), rand.nextInt(3) + 1);
	}
	
}
