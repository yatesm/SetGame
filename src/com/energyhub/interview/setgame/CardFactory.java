package com.energyhub.interview.setgame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * CardFactory class
 * <p>
 *
 * Simple static-method factory for creating cards.  Could be extended to encode an actual deck from which to
 * draw from.
 */
public class CardFactory {
	private static final Random rand = new Random();
	private static final Map<String, List<String>> descriptorMap = new HashMap<String, List<String>>();

    /**
     * Initializes the card factory.  Caller passes the name or path to a file.  The file contains a list of filenames
     * or paths, one per line.  Each of those files encodes a descriptor 'type', and the file contains a list of
     * descriptors.
     *
     * For each line in the filename, a call to readDescriptorFile is made, which populates the descriptorMap with
     * descriptor types and their values.
     *
     * @param filename name of the input file containing descriptor files.
     */
	public static void initializeCardFactory(String filename) {
        BufferedReader reader = null;
        String descriptorFileName = null;
		try {
            reader = new BufferedReader(new FileReader(filename));
            while((descriptorFileName = reader.readLine()) != null)
                readDescriptorFile(descriptorFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
            System.err.println("Cannot read the file containing descriptor files");
            System.err.println("This will be a very boring game with nothing but blank cards");
            System.exit(1);
		} catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot read the file containing descriptor files");
            System.err.println("This will be a very boring game with nothing but blank cards");
            System.exit(1);
        } finally {
            if(reader != null) try { reader.close(); } catch(Exception e) {} finally {}
        }
	}

    /**
     * Function to populate the descriptor list map with descriptors.  The file should contain one descriptor per line.
     * Each line is encoded into a string an added to an ArrayList.  The ArrayList is inserted into the descriptorMap
     * with the name of the file (with any path and file extension information removed) as its key
     *
     * @param filename name of the file containing descriptors corresponding to that 'type' of descriptor.
     */

    public static void readDescriptorFile(String filename) {

        BufferedReader reader = null;
        String descriptorName, descriptor;

        //Trim off any path to the file from the filename
        if(filename.contains("/"))
            descriptorName = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
        else
            descriptorName = filename;

        //Trim off any extension to the file name
        if(filename.contains(".txt"))
            descriptorName = descriptorName.substring(0, descriptorName.indexOf(".txt"));
        else
            descriptorName = filename;

        List<String> descriptorList = new ArrayList<String>();

        //Read in the descriptor file.
        try {
            reader = new BufferedReader(new FileReader(filename));
            while((descriptor = reader.readLine()) != null)
                descriptorList.add(descriptor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not find file: " + filename + ".  Are you sure it exists?");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not access file: " + filename + ".  Are you sure permissions are properly set?");
            return;
        } finally {
            if(reader != null) try { reader.close(); } catch(Exception e) {} finally {}
        }
        descriptorMap.put(descriptorName, descriptorList);
    }

    /**
     * Draws a random card
     * @return A card constructed with random descriptors, one descriptor from each 'type' of descriptor.
     */

	public static Card drawCard() {
        Map<String, String> cardDescriptorMap = new HashMap<String, String>();
        String [] keys = CardFactory.descriptorMap.keySet().toArray(new String[descriptorMap.size()]);
        for(String s : keys) {
            List<String> descriptorList = CardFactory.descriptorMap.get(s);
            cardDescriptorMap.put(s, descriptorList.get(rand.nextInt(descriptorList.size())));
        }
        return new Card(cardDescriptorMap);

    }

    /**
     * Returns the keyset of the descriptorMap.  Which is to say, it returns the set containing all 'types' of
     * descriptors.
     * @return Set<String> objects containing all descriptor 'types' in the descriptor map.
     */
    public static Set<String> getDescriptorKeySet() {
        return descriptorMap.keySet();
    }

    /**
     * Returns a List<String>, containing all descriptors in the descriptorMap, that correspondings to the passed key
     * parameter.
     * @param key String describing the key or 'type' of descriptor wanted.
     * @return List<String> containing all descriptors for the key 'type' descriptor; null if key does not exist in
     *      descriptor map.
     */
    public static List<String> getDescriptorList(String key) {
        return descriptorMap.get(key);
    }

    /**
     * Clears the descriptorMap.  Used exclusively in testing purposes.
     */
    public static void clear() {
        descriptorMap.clear();
    }
}
