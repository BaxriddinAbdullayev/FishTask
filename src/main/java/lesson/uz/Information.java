package lesson.uz;

import java.util.concurrent.atomic.AtomicInteger;

public class Information {

    public static AtomicInteger allFish = new AtomicInteger(0);
    public static AtomicInteger maleFish = new AtomicInteger(1);
    public static AtomicInteger femaleFish = new AtomicInteger(1);
    public static AtomicInteger diedFish = new AtomicInteger(0);

    public static void start() {
        System.out.println("\n=====================     Start     =====================\n");

        while (true) {

            allFish.set(Aquarium.fishList.size());

            System.out.println("All fish: " + allFish+
                    "\nMale fish: " + maleFish +
                    "\nFemale fish: " + femaleFish +
                    "\nDied fish: " + diedFish +"\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(allFish.get() == 0) {
                break;
            }
        }
        System.out.println("=====================     Finish     =====================");
    }
}
