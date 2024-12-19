package lesson.uz;

public class Main {

    public static void main(String[] args) {

        Fish fish1 = new Fish(Gender.MALE);
        fish1.start();
        Aquarium.fishList.add(fish1);

        try {
            Thread.sleep(1_00);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Fish fish2 = new Fish(Gender.FEMALE);
        fish2.start();
        Aquarium.fishList.add(fish2);

        Information.start();
    }

}