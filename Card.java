package com.energyhub.interview.setgame;

public class Card {
	private final Color color;
	private final Shading shading;
	private final Symbol symbol;
	private final Integer number;
	
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
		this.number = new Integer(number);
	}
	
	@Override
	public String toString() {
		String s = number + " " + shading + " " + color + " " + symbol;
		if(number > 1)
			s = s + "S";
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
	
	public Integer getNumber() {
		return number;
	}
	
}
