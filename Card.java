package com.energyhub.interview.setgame;

public class Card implements Comparable<Card> {
	private final Color color;
	private final Shading shading;
	private final Symbol symbol;
	private final Integer number;
	
	/**
	 * Generates a card given the color, shading, symbol and number on the card.
	 *
	 * @param color color of the symbols on the card
	 * @param shading shading of the color on the card
	 * @param symbol symbol on the card
	 * @param number number of symbols on the card
	 */
	public Card(Color color, Shading shading, Symbol symbol, int number) {
		this.color = color;
		this.shading = shading;
		this.symbol = symbol;
		this.number = new Integer(number);
	}

	/**
	 * toString() function.  Produces pleasant-on-the-eyes (barring caps) string-representation of the card.
	 *
	 * @return string representation of the card
     */
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

	public int compareTo(Card c) {
		int returnValue = 0;
		if(!this.color.equals(c.color) || !this.symbol.equals(c.symbol) || !this.shading.equals(c.shading) ||
				!this.number.equals(c.number))
			returnValue = -1;
		return returnValue;

	}
}
