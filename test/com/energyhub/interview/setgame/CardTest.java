package com.energyhub.interview.setgame;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Unit Tests for Card Class
 */
public class CardTest {

    private Card c1;
    private Card c2;
    private Card c3;

    /**
     * setUp function for Unit Tests
     *
     * Instantiates three cards:
     *  3 solid red diamonds
     *  2 striped green squiggles
     *  1 outlined purple oval
     */
    @Before
    public void setUp() {
        Map<String, String> descriptorMap = new HashMap<String, String>();
        descriptorMap.put("color", "red");
        descriptorMap.put("shading", "solid");
        descriptorMap.put("symbol", "diamond");
        descriptorMap.put("number", "3");
        c1 = new Card(descriptorMap);

        descriptorMap = new HashMap<String, String>();
        descriptorMap.put("color", "green");
        descriptorMap.put("shading", "striped");
        descriptorMap.put("symbol", "squiggle");
        descriptorMap.put("number", "2");
        c2 = new Card(descriptorMap);

        descriptorMap = new HashMap<String, String>();
        descriptorMap.put("color", "purple");
        descriptorMap.put("shading", "outlined");
        descriptorMap.put("symbol", "oval");
        descriptorMap.put("number", "1");
        c3 = new Card(descriptorMap);

    }

    /**
     * Unit test for getDescriptor
     *
     * Ensures the the getDescriptor function of a card returns the expected value for that descriptor from
     * instantiation.
     *
     * @throws Exception  Throws any number of exceptions in the event of assertion failure.
     */
    @Test
    public void getDescriptor() throws Exception {
        assertTrue(c1.getDescriptor("color").compareTo("red") == 0);
        assertTrue(c2.getDescriptor("color").compareTo("green") == 0);
        assertTrue(c3.getDescriptor("color").compareTo("purple") == 0);

        assertTrue(c1.getDescriptor("shading").compareTo("solid") == 0);
        assertTrue(c2.getDescriptor("shading").compareTo("striped") == 0);
        assertTrue(c3.getDescriptor("shading").compareTo("outlined") == 0);

        assertTrue(c1.getDescriptor("symbol").compareTo("diamond") == 0);
        assertTrue(c2.getDescriptor("symbol").compareTo("squiggle") == 0);
        assertTrue(c3.getDescriptor("symbol").compareTo("oval") == 0);

        assertTrue(c1.getDescriptor("number").compareTo("3") == 0);
        assertTrue(c2.getDescriptor("number").compareTo("2") == 0);
        assertTrue(c3.getDescriptor("number").compareTo("1") == 0);
    }

    /**
     * Unit test for Comparable.compareTo()
     *
     * Ensures that a card compared to itself yields an equality result
     * Ensures that two non-equal cards yields an inequality result.
     *
     * @throws Exception  Throws any number of exceptions in the event of assertion failure.
     */
    @Test
    public void compareTo() throws Exception {
        assertTrue(c1.compareTo(c1) == 0);
        assertTrue(c1.compareTo(c2) != 0);
        assertTrue(c1.compareTo(c3) != 0);
        assertTrue(c2.compareTo(c2) == 0);
        assertTrue(c2.compareTo(c3) != 0);
        assertTrue(c3.compareTo(c3) == 0);
    }

    /**
     * Unit test for getDescriptorKeySet()
     *
     * Ensures that each descriptor retrieved from the getDescriptor method is contained within the Set<String> object
     * returned by getDescriptorKeySet.
     *
     * @throws Exception  Throws any number of exceptions in the event of assertion failure.
     */
    @Test
    public void getDescriptorKeySet() throws Exception {
        Set<String> set = c1.getDescriptorKeySet();
        for(String s : set)
            assertTrue(c1.getDescriptor(s) != null);

        set = c2.getDescriptorKeySet();
        for(String s : set)
            assertTrue(c2.getDescriptor(s) != null);

        set = c3.getDescriptorKeySet();
        for(String s : set)
            assertTrue(c3.getDescriptor(s) != null);
    }
}