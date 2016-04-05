/**
 * 
 */
package com.energyhub.interview.setgame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.energyhub.interview.setgame.descriptors.Color;

/**
 * @author JYMONTE
 *
 */
public class ColorTest {
	private Color c1;
	private Color c2;
	private Color c3;
	private Color c4;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c1 = Color.RED;
		c2 = Color.PURPLE;
		c3 = Color.GREEN;
		c4 = Color.RED;
		
	}
	
	@Test
	public void testEquality() {
		assertEquals(c1, c4);
		assertNotEquals(c1, c2);
		assertNotEquals(c1, c3);
		assertNotEquals(c2, c3);
	}
	
	@Test
	public void testGetRandomColor() {
		for(int i = 0; i < 10; i++) {
			Color c = Color.getRandomColor();
			assertNotEquals(c, null);
			System.out.println(c);
		}		
	}

}
