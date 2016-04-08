package com.energyhub.interview.setgame;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Unit Tests for CardFactory class.
 */
public class CardFactoryTest {
    private String filename = "test/input/descriptors.txt";

    /**
     * Unit test for drawCard
     *
     * Ensures the randomly generated card from CardFactory is not null.
     *
     * @throws Exception
     */
    @Test
    public void drawCard() throws Exception {
        Card c = CardFactory.drawCard();
        assertNotNull(c);
    }

    /**
     * Unit test for initializeCardFactory.
     *
     * Ensures that the CardFactory creates one <Key, List<String>> pair for each input file found in
     *  CardFactoryTest.filename
     * @throws Exception
     */
    @Test
    public void initializeCardFactory() throws Exception {

        CardFactory.initializeCardFactory(filename);
        Set<String> keySet = CardFactory.getDescriptorKeySet();
        assertTrue(keySet.size() == 4);
        List<String> descriptorList = CardFactory.getDescriptorList("number");
        assertTrue(descriptorList.size() == 3);
        assertTrue(descriptorList.contains("1"));
        assertTrue(descriptorList.contains("2"));
        assertTrue(descriptorList.contains("3"));
        descriptorList = CardFactory.getDescriptorList("shading");
        assertTrue(descriptorList.size() == 3);
        assertTrue(descriptorList.contains("solid"));
        assertTrue(descriptorList.contains("outlined"));
        assertTrue(descriptorList.contains("striped"));
        descriptorList = CardFactory.getDescriptorList("symbol");
        assertTrue(descriptorList.size() == 3);
        assertTrue(descriptorList.contains("squiggle"));
        assertTrue(descriptorList.contains("oval"));
        assertTrue(descriptorList.contains("diamond"));
        descriptorList = CardFactory.getDescriptorList("color");
        assertTrue(descriptorList.size() == 3);
        assertTrue(descriptorList.contains("red"));
        assertTrue(descriptorList.contains("green"));
        assertTrue(descriptorList.contains("purple"));
        CardFactory.clear();
    }

    /**
     * Unit test for readDescriptorFile
     *
     * Given the descriptor file test/input/number.txt, ensures that the <key, value> pair
     * <"number", List<String>={"1", "2", "3"}> is instantiated within the CardFactory.descriptorMap HashMap.
     *
     * @throws Exception
     */
    @Test
    public void readDescriptorFile() throws Exception {
        String filename = "test/input/number.txt";

        CardFactory.readDescriptorFile(filename);
        Set<String> keySet = CardFactory.getDescriptorKeySet();
        assertTrue(keySet.size() == 1);
        List<String> descriptorList = CardFactory.getDescriptorList("number");
        assertNotNull(descriptorList);
        System.out.println(descriptorList.size());
        assertTrue(descriptorList.size() == 3);
        assertTrue(descriptorList.contains("1"));
        assertTrue(descriptorList.contains("2"));
        assertTrue(descriptorList.contains("3"));
        CardFactory.clear();
    }

    /**
     * getDescriptorList
     *
     * Ensures the KeySet of CardFactory.descriptorMap contains one entry for the given input file.
     * Ensures the List<String> corresponding to the descriptorMap key "number" contains 3 entries from the given input
     * file.
     */
    @Test
    public void getDescriptorList() {
        CardFactory.clear();
        String filename = "test/input/number.txt";

        CardFactory.readDescriptorFile(filename);
        Set<String> keySet = CardFactory.getDescriptorKeySet();
        assertTrue(keySet.size() == 1);
        List<String> descriptorList = CardFactory.getDescriptorList("number");
        assertTrue(descriptorList.size() == 3);
        assertTrue(descriptorList.contains("1"));
        assertTrue(descriptorList.contains("2"));
        assertTrue(descriptorList.contains("3"));
        CardFactory.clear();
    }

    @Test
    /**
     * Unit test for Clear
     *
     * Ensures the contents of the keySet of CardFactory.descriptorMap is empty after clear has been called, implying
     * the emptiness of CardFactory.descriptorMap after the clear() call.
     */
    public void clear() {
        String filename = "test/input/number.txt";

        CardFactory.readDescriptorFile(filename);
        Set<String> keySet = CardFactory.getDescriptorKeySet();
        assertTrue(keySet.size() == 1);
        CardFactory.clear();
        keySet = CardFactory.getDescriptorKeySet();
        assertTrue(keySet.size() == 0);
        CardFactory.clear();
    }

}