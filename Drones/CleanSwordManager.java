package Drones;

import CommonUtils.BetterQueue;

import java.io.*;

import java.util.ArrayList;

/**
 * Manages everything regarding the cleaning of swords in our game.
 * Will be integrated with the other drone classes.
 * <p>
 * You may only use java.util.List, java.util.ArrayList, and java.io.* from
 * the standard library.  Any other containers used must be ones you created.
 */
public class CleanSwordManager implements CleanSwordManagerInterface {
    /**
     * Gets the cleaning times per the specifications.
     *
     * @param filename file to read input from
     * @return the list of times requests were filled and times it took to fill them, as per the specifications
     */
    @Override
    public ArrayList<CleanSwordTimes> getCleaningTimes(String filename) {
        //todo: implement this method
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line = bf.readLine();
            String[] lineArray = line.split(" ");

            long numClean = Long.parseLong(lineArray[0]);
            long numReq = Long.parseLong(lineArray[1]);
            long numTime = Long.parseLong(lineArray[2]);
            bf.close();


            long[] cleanSwords = new long[(int) numClean];
            long[] reqSwords = new long[(int) numReq];
            BufferedReader bf2 = new BufferedReader(new FileReader(filename));
            bf2.readLine();
            String line2;
            String[] datArray = new String[(int) (numClean + numReq)];

            int count = 0;
            while (count < numClean + numReq) {
                while ((line2 = bf2.readLine()) != null) {
                    datArray[count] = line2;
                    count++;
                }
            }

            bf2.close();

            for (int i = 0; i < numClean; i++) {
                cleanSwords[i] = Long.parseLong(datArray[i]);
                if (i > 0) {
                    cleanSwords[i] += cleanSwords[i - 1];
                }
            }
            int check1 = 0;
            int check2 = (int) numClean;
            while (check1 < numReq) {
                reqSwords[check1] = Long.parseLong(datArray[check2]);
                check1++;
                check2++;
            }

            BetterQueue<Long> cleanQueue = new BetterQueue<>();

            for (long cleanSword : cleanSwords) {
                cleanQueue.add((long) cleanSword);
            }

            long timeFilled = 0;
            long timeToFill;
            ArrayList<CleanSwordTimes> times = new ArrayList<>((int) numReq);
            CleanSwordTimes time;
            long cleanSword;
            for (int i = 0; i < numReq; i++) {
                cleanSword = cleanQueue.remove();
                long newTime = 0;
                if (cleanQueue.size() > 0) {
                    newTime = (Long.max(reqSwords[i], cleanQueue.getBack()) + numTime);
                } else {
                    newTime = (Long.max(reqSwords[i], cleanSword) + numTime);
                }
                cleanQueue.add(newTime);


                timeFilled = Long.max(cleanSword, reqSwords[i]);
                timeToFill = timeFilled - reqSwords[i];


                if (timeToFill <= 0) {
                    timeFilled = Long.max(cleanSword, reqSwords[i]);
                    timeToFill = 0;
                } else {
                    timeFilled = Long.max(cleanSword, reqSwords[i]);
                    timeToFill = timeFilled - reqSwords[i];
                }

                time = new CleanSwordTimes(timeFilled, timeToFill);
                times.add(time);
            }


            return times;

        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }


}
