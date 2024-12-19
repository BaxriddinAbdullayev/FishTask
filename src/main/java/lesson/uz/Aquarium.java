package lesson.uz;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Aquarium {

    public static CopyOnWriteArrayList<Fish> fishList = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Fish> adultMaleFishList = new CopyOnWriteArrayList<>();

    public static synchronized Fish getRandomMaleFish() {
        if(adultMaleFishList.isEmpty()) {
            return null;
        }
        Fish selectedFish = adultMaleFishList.get(new Random().nextInt(adultMaleFishList.size()));

        selectedFish.setSpawnInterval(0);
        adultMaleFishList.remove(selectedFish);
        return selectedFish;
    }
}
