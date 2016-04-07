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
 * Simple static-method based factory for creating cards.  Could be extended to encode an actual deck from which to
 * draw from.
 */
public class CardFactory {
	private static final Random rand = new Random();
	private static final Map<String, List<String>> descriptorMap = new HashMap<String, List<String>>();

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

    public static void readDescriptorFile(String filename) {

        BufferedReader reader = null;
        String descriptorName, descriptor;

        if(filename.contains(".txt"))
            descriptorName = filename.substring(0, filename.indexOf(".txt"));
        else
            descriptorName = filename;
        List<String> descriptorList = new ArrayList<String>();

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

	public Card drawCard() {

    }
	
}
