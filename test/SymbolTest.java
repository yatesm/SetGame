package com.energyhub.interview.setgame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.energyhub.interview.setgame.descriptors.Symbol;

public class SymbolTest {
	
	private Symbol s1;
	private Symbol s2;
	private Symbol s3;
	private Symbol s4;
	
	@Before
	public void setUp() {
		s1 = Symbol.DIAMOND;
		s2 = Symbol.OVAL;
		s3 = Symbol.SQUIGGLE;
		s4 = Symbol.DIAMOND;
	}
	
	@Test
	public void testGetRandomSymbol() {
		for(int i = 0; i < 10; i++) {
			Symbol symbol = Symbol.getRandomSymbol();
			assertNotEquals(symbol, null);
			System.out.println(symbol);
		}
			
	}
	
	@Test
	public void testEquality() {
		assertEquals(s1, s4);
		assertNotEquals(s1, s2);
		assertNotEquals(s1, s3);
		assertNotEquals(s2, s3);
	}

}
