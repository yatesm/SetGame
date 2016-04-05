/**
 * 
 */
package com.energyhub.interview.setgame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.energyhub.interview.setgame.Card;
import com.energyhub.interview.setgame.Hand;
import com.energyhub.interview.setgame.descriptors.Color;
import com.energyhub.interview.setgame.descriptors.Shading;
import com.energyhub.interview.setgame.descriptors.Symbol;

/**
 * @author JYMONTE
 *
 */
public class HandTest {
	private Hand h1;
	private Hand h2;
	private Hand h3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Hand for testing all same, result should be isSet for all.
		h1 = new Hand();
		h1.addCard(new Card(Color.PURPLE, Shading.SOLID, Symbol.DIAMOND, 1));
		h1.addCard(new Card(Color.PURPLE, Shading.SOLID, Symbol.DIAMOND, 1));
		h1.addCard(new Card(Color.PURPLE, Shading.SOLID, Symbol.DIAMOND, 1));
		
		//Hand for testing all different, result should be isSet for all.
		h2 = new Hand();
		h2.addCard(new Card(Color.PURPLE, Shading.SOLID, Symbol.DIAMOND, 1));
		h2.addCard(new Card(Color.GREEN, Shading.STRIPED, Symbol.OVAL, 2));
		h2.addCard(new Card(Color.RED, Shading.OUTLINED, Symbol.SQUIGGLE, 3));
		
		//Hand for testing non-set hand
		h3 = new Hand();
		h3.addCard(new Card(Color.PURPLE, Shading.SOLID, Symbol.DIAMOND, 1));
		h3.addCard(new Card(Color.PURPLE, Shading.STRIPED, Symbol.DIAMOND, 1));
		h3.addCard(new Card(Color.RED, Shading.OUTLINED, Symbol.DIAMOND, 2));
	}

	/**
	 * Test method for {@link com.energyhub.interview.setgame.Hand#isSet()}.
	 */
	@Test
	public void testIsSet() {
		//Expected Outcome True
		assertTrue(h1.isSet());
		//Expected Outcome True
		assertTrue(h2.isSet());
		//Expected Outcome False
		assertFalse(h3.isSet());
		
	}
	
	@Test 
	public void testIsColorSet() {
		//Expected Outcome True
		assertTrue(h1.isColorSet());
		//Expected Outcome True
		assertTrue(h2.isColorSet());
		//Expected Outcome False
		assertFalse(h3.isColorSet());
	}
	
	@Test
	public void testIsSymbolSet() {
		//Expected Outcome True
		assertTrue(h1.isSymbolSet());
		//Expected Outcome True
		assertTrue(h2.isSymbolSet());
		//Expected Outcome True
		assertTrue(h3.isSymbolSet());
	}
	
	@Test
	public void testIsNumberSet() {
		//Expected Outcome True
		assertTrue(h1.isNumberSet());
		//Expected Outcome True
		assertTrue(h2.isNumberSet());
		//Expected Outcome False
		assertFalse(h3.isNumberSet());
	}
	
	public void testIsShadingSet() {
		//Expected Outcome True
		assertTrue(h1.isShadingSet());
		//Expected Outcome True
		assertTrue(h2.isShadingSet());
		//Expected Outcome True
		assertTrue(h3.isShadingSet());
	}
	
	

}
