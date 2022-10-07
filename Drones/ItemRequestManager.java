package Drones;

import CommonUtils.BetterStack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Manages everything regarding the requesting of items in our game.
 * Will be integrated with the other drone classes.
 *
 * You may only use java.util.List, java.util.ArrayList, java.io.* and java.util.Scanner
 * from the standard library.  Any other containers used must be ones you created.
 */
public class ItemRequestManager implements ItemRequestManagerInterface {
    /**
     * Get the retrieval times as per the specifications
     *
     * @param filename file to read input from
     * @return the list of times requests were filled and index of the original request, per the specifications
     */
    @Override
    public ArrayList<ItemRetrievalTimes> getRetrievalTimes(String filename) {
        try {
            // as all of the inputs are on the same line, it is actually more efficient to use scanner's nextInt since
            // with BufferedReader you would have to read in the entire line (possibly 10m integers long) at once
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line = bf.readLine();
            String[] lineArray = line.split(" ");
            int numItems = Integer.parseInt(lineArray[0]); //number of items in the warehouse
            int timeToStorage = Integer.parseInt(lineArray[1]);
            System.out.println("numItems: " + numItems + " timeToStorage " + timeToStorage);
            line = bf.readLine();
            String[] tempString = line.split(" ");
            System.out.println("tempString: " + tempString.length);
            int[] timeOfReq = new int[numItems];
            for (int i = 0; i < numItems; i++) {
                timeOfReq[i] = Integer.parseInt(tempString[i]);
            }
            bf.close();

            BetterStack<Integer> stack = new BetterStack<>();


            //todo
            //create a list of retrieval times
            ArrayList<ItemRetrievalTimes> retrievalTimes = new ArrayList<>();


            //create a list of items in the warehouse


        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }

        return null;
    }
}
