package lesson.uz.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Container {
    private final List<Fish> fishList;

    public Container(List<Fish> fishList) {
        this.fishList = fishList;
    }

    public void addFish(Fish fish) {
        fishList.add(fish);
    }

    public void tick() {
        fishList.removeIf(Fish::isDead);

        List<Fish> babies = new ArrayList<>();
        for (Fish fish : fishList) {
            fish.tick();
            Fish baby = fish.giveBirth();
            if (baby != null) {
                babies.add(baby);
            }
        }

        fishList.addAll(babies);

        List<Fish> maleFish = fishList.stream().filter(it -> it.getGender() == Fish.GENDER.MALE).collect(Collectors.toList());
        List<Fish> femaleFish = fishList.stream().filter(it -> it.getGender() == Fish.GENDER.FEMALE).collect(Collectors.toList());

        int femaleCounter = 0;

        for (Fish male : maleFish) {
            if (!male.isLookingForPartner()) {
                continue;
            }

            Fish female = null;
            while (female == null || !female.isLookingForPartner()) {
                if (femaleCounter == femaleFish.size()) {
                    return;
                }
                female = femaleFish.get(femaleCounter);
                femaleCounter++;
            }

            female.getPregnant();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Container container = new Container(new ArrayList<>());
        container.addFish(new Fish(0, Fish.GENDER.FEMALE, false));
        container.addFish(new Fish(0, Fish.GENDER.MALE, false));
        int iteration = 0;
        while (true) {
            container.tick();
            Thread.sleep(300);
            iteration++;
            System.out.printf("Iteration {}", iteration);
        }
    }

}
