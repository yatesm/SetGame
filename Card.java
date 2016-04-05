package com.energyhub.interview.setgame;

import com.energyhub.interview.setgame.descriptors.Color;
import com.energyhub.interview.setgame.descriptors.Shading;
import com.energyhub.interview.setgame.descriptors.Symbol;

public class Card {
	private final Color color;
	private final Shading shading;
	private final Symbol symbol;
	private final int number;
	
	/**
	 * 
	 * @param color
	 * @param shading
	 * @param symbol
	 * @param number
	 */
	public Card(Color color, Shading shading, Symbol symbol, int number) {
		this.color = color;
		this.shading = shading;
		this.symbol = symbol;
		this.number = number;
	}
	
	@Override
	public String toString() {
		String s = number + " " + color + " " + shading + " " + symbol;
		if(number > 1)
			s = s + "s";
		return s;
				
	}
	
	/** Getters **/
	
	public Color getColor() {
		return color;
	}
	
	public Shading getShading() {
		return shading;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public int getNumber() {
		return number;
	}
	
}
