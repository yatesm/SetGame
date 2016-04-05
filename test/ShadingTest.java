/**
 * 
 */
package com.energyhub.interview.setgame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.energyhub.interview.setgame.descriptors.Shading;

/**
 * @author JYMONTE
 *
 */
public class ShadingTest {

	private Shading s1;
	private Shading s2;
	private Shading s3;
	private Shading s4;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		s1 = Shading.SOLID;
		s2 = Shading.OUTLINED;
		s3 = Shading.STRIPED;
		s4 = Shading.SOLID;
	}
	
	@Test
	public void testEquality() {
		assertEquals(s1, s4);
		assertNotEquals(s1, s2);
		assertNotEquals(s1, s3);
		assertNotEquals(s2, s3);
	}

	/**
	 * Test method for {@link com.energyhub.interview.setgame.descriptors.Shading#getRandomShading()}.
	 */
	@Test
	public void testGetRandomShading() {
		for(int i = 0; i < 10; i++) {
			Shading shading = Shading.getRandomShading();
			assertNotNull(shading);
			System.out.println(shading);
		}
			
	}

}
