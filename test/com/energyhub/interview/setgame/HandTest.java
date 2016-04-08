package com.energyhub.interview.setgame;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Units tests for Hand class.
 */
public class HandTest {
    private Hand h1;
    private Hand h2;
    private Hand h3;
    private Card c1;


    /**
     * Instantiates objects for Hand class unit tests.
     *
     * Creates three hand objects, h1, h2 and h3.
     * Creates three card objects to populate the hand objects with.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        h1 = new Hand();
        h2 = new Hand();
        h3 = new Hand();
        Map<String, String> descriptorMap = new HashMap<String, String>();
        descriptorMap.put("color", "red");
        descriptorMap.put("shading", "solid");
        descriptorMap.put("symbol", "diamond");
        descriptorMap.put("number", "3");
        c1 = new Card(descriptorMap);

        h1.addCard(c1);
        h2.addCard(c1);
        h3.addCard(c1);

        descriptorMap = new HashMap<String, String>();
        descriptorMap.put("color", "green");
        descriptorMap.put("shading", "striped");
        descriptorMap.put("symbol", "squiggle");
        descriptorMap.put("number", "2");
        c1 = new Card(descriptorMap);

        h2.addCard(c1);
        h3.addCard(c1);

        descriptorMap = new HashMap<String, String>();
        descriptorMap.put("color", "purple");
        descriptorMap.put("shading", "outlined");
        descriptorMap.put("symbol", "oval");
        descriptorMap.put("number", "1");
        c1 = new Card(descriptorMap);
        h3.addCard(c1);

    }

    /**
     * getCard unit tests
     *
     * Tests the following cases:
     *  Ensures getting a card from an empty hand returns a null value.
     *  Ensures getting a card with a negative index returns a null value.
     *  Ensures getting a card with an index that exceeds the size of the data structure containing cards returns a null
     *      value.
     *  Ensures getting a card does not alter the descriptor values for that card.
     *
     * @throws Exception Throws any number of exceptions if assertions fail.
     */
    @Test
    public void getCard() throws Exception {
        Hand h4 = new Hand();
        Card c0 = h4.getCard(0);
        assertTrue(c0 == null);
        c0 = h4.getCard(-1);
        assertTrue(c0 == null);
        c0 = h4.getCard(10);
        assertTrue(c0 == null);
        h4.addCard(c1);
        assertTrue(h4.getCard(0) != null);
        Card c2 = h4.getCard(0);
        assertTrue(c1.getDescriptor("color").compareTo(c2.getDescriptor("color")) == 0);
        assertTrue(c1.getDescriptor("shading").compareTo(c2.getDescriptor("shading")) == 0);
        assertTrue(c1.getDescriptor("symbol").compareTo(c2.getDescriptor("symbol")) == 0);
        assertTrue(c1.getDescriptor("number").compareTo(c2.getDescriptor("number")) == 0);
    }

    /**
     * Unit test for addCard
     *
     * Tests the following cases:
     *  Ensures that adding a card to a hand does not alter the descriptor values from that hand.  Depends on getCard()
     * @throws Exception Throws any number of exceptions if assertions fail
     */
    @Test
    public void addCard() throws Exception {
        Hand h4 = new Hand();
        h4.addCard(c1);
        assertTrue(h4.size() == 1);
        assertTrue(h4.getCard(0) != null);
        Card c2 = h4.getCard(0);
        assertTrue(c1.getDescriptor("color").compareTo(c2.getDescriptor("color")) == 0);
        assertTrue(c1.getDescriptor("shading").compareTo(c2.getDescriptor("shading")) == 0);
        assertTrue(c1.getDescriptor("symbol").compareTo(c2.getDescriptor("symbol")) == 0);
        assertTrue(c1.getDescriptor("number").compareTo(c2.getDescriptor("number")) == 0);
    }

    /**
     * Unit test for toString
     *
     * Tests the following cases:
     *  Ensures that toString() card has expected representation
     * @throws Exception Throws any number of exceptions if assertions fail
     */
    @Test
    public void toStringTest() throws Exception {
        String s1 = h1.toString();
        //String t1 = "red solid 3 diamond\n";
        String t1 = "red 3 solid diamond\n";
        System.out.println(s1);
        assertTrue(s1.compareTo(t1) == 0);
    }

    /**
     * Unit test for determineIsSet
     *
     * Tests the following cases:
     *  Ensures setness for hands containing a single card.
     *  Ensures setness for hands containing two cards, each with differing descriptors.
     *  Ensures setness for hands containing three cards, each with different descriptors.
     *  Ensures setness for hands containing four cards, with two cards containing common descriptors.
     *  Enures setness for hands containig two cards, each with the same descriptors.
     * Relies on Card.addCard
     *
     * @throws Exception Throws any number of exceptions if assertions fail
     */
    @Test
    public void determineIsSet() throws Exception {
        assertTrue(h1.determineIsSet("color"));
        assertTrue(h1.determineIsSet("number"));
        assertTrue(h1.determineIsSet("symbol"));
        assertTrue(h1.determineIsSet("shading"));

        assertTrue(h2.determineIsSet("color"));
        assertTrue(h2.determineIsSet("number"));
        assertTrue(h2.determineIsSet("symbol"));
        assertTrue(h2.determineIsSet("shading"));

        assertTrue(h3.determineIsSet("color"));
        assertTrue(h3.determineIsSet("number"));
        assertTrue(h3.determineIsSet("symbol"));
        assertTrue(h3.determineIsSet("shading"));

        h3.addCard(c1);

        assertFalse(h3.determineIsSet("color"));
        assertFalse(h3.determineIsSet("number"));
        assertFalse(h3.determineIsSet("symbol"));
        assertFalse(h3.determineIsSet("shading"));

        Card c = h1.getCard(0);
        h1.addCard(c);
        assertTrue(h1.determineIsSet("color"));
        assertTrue(h1.determineIsSet("number"));
        assertTrue(h1.determineIsSet("symbol"));
        assertTrue(h1.determineIsSet("shading"));

    }

    /**
     * Unit test for isSet
     *
     * Tests the following cases:
     *  Ensures setness of a hand containing one card.
     *  Ensures setness for hands containing two cards, each with differing descriptors.
     *  Ensures setness for hands containing three cards, each with different descriptors.
     *  Ensures setness for hands containing four cards, with two cards containing common descriptors.
     * Depends on determineIsSet()
     *
     * @throws Exception Throws any number of exceptions if assertions fail
     */
    @Test
    public void isSet() throws Exception {
        //Expected Outcome True
        assertTrue(h1.isSet());
        //Expected Outcome True
        assertTrue(h2.isSet());
        //Expected Outcome False
        assertTrue(h3.isSet());
        //Add duplicate card to h3
        h3.addCard(c1);
        //Expected outcome false
        assertFalse(h3.isSet());
    }

    /**
     * Unit test for Comparable.compareTo()
     *
     * Tests the following cases:
     *  Ensures a hand is equal to itself.
     *  Ensures two differing hands are not equal.
     *
     * @throws Exception Throws any number of exceptions if assertions fail
     */
    @Test
    public void compareTo() throws Exception {
        assertTrue(h1.compareTo(h1) == 0);
        assertTrue(h2.compareTo(h2) == 0);
        assertTrue(h3.compareTo(h3) == 0);
        assertTrue(h1.compareTo(h2) != 0);
        assertTrue(h1.compareTo(h3) != 0);
        assertTrue(h2.compareTo(h3) != 0);
    }
}
