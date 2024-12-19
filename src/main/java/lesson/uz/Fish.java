package lesson.uz;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Fish extends Thread {

    private Gender gender;
    private Integer lifespan;
    private String fishName;
    private Integer age = 0;
    private boolean isAddedToList;
    private Integer spawnInterval;

    public Fish(Gender gender) {
        this.gender = gender;
        this.lifespan = new Random().nextInt(6) + 10;
        this.isAddedToList = false;
        this.spawnInterval = -1;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);

                if(this.age >= this.lifespan){
                    if(this.gender == Gender.MALE){
                        Aquarium.adultMaleFishList.remove(this);
                        Aquarium.fishList.remove(this);
                        Information.maleFish.decrementAndGet();
                    }else{
                        Aquarium.fishList.remove(this);
                        Information.femaleFish.decrementAndGet();
                    }
                    Information.diedFish.incrementAndGet();
                    Thread.currentThread().interrupt();
                }

                if(spawnInterval == 0 || spawnInterval == 1 || spawnInterval == 2 || spawnInterval == 3) {
                    if(spawnInterval == 3){
                        if(gender == Gender.MALE){
                            Aquarium.adultMaleFishList.add(this);
                        }
                        spawnInterval = -1;
                    }else {
                        spawnInterval++;
                    }
                }

                if(gender == Gender.FEMALE && age>=4 && spawnInterval==-1){
                    reproduce();
                }

                if(!isAddedToList && gender == Gender.MALE && age>=4){
                    Aquarium.adultMaleFishList.add(this);
                    isAddedToList = true;
                }

                age++;
            } catch (InterruptedException e) {
            }
        }
    }

    public static Fish createFish() {
        Fish newFish = new Fish(Math.random() > 0.5 ? Gender.MALE : Gender.FEMALE);
        newFish.start();
        if (newFish.getGender() == Gender.MALE) {
            Information.maleFish.incrementAndGet();
        }else {
            Information.femaleFish.incrementAndGet();
        }
        return newFish;
    }

    public void reproduce() {
        Fish randomFish = Aquarium.getRandomMaleFish();

        if(randomFish == null){
            return;
        }

        this.spawnInterval = 0;
        Fish babyFish = createFish();
        Aquarium.fishList.add(babyFish);
    }
}
