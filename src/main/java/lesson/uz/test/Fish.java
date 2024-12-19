package lesson.uz.test;

import lombok.Getter;

public class Fish {
    int age;
    @Getter
    GENDER gender;
    boolean pregnant;

    public Fish(int i, GENDER randomGender, boolean b) {
        this.age = 0;
        this.gender = randomGender;
        this.pregnant= b;
    }

    enum GENDER {
        MALE,
        FEMALE
    }

    public boolean isLookingForPartner() {
        return !isDead() && age >= 2 && !pregnant;
    }

    public void getPregnant() {
        if (gender == GENDER.FEMALE) {
            pregnant = true;
        }
    }

    public Fish giveBirth() {
        if (pregnant) {
            pregnant = false;
            //generate random gender
            GENDER randomGender = Math.random() < 0.5 ? GENDER.MALE : GENDER.FEMALE;
            System.out.println("NEW BABY FISH");
            return new Fish(0, randomGender, false);
        }

        return null;
    }

    public boolean isDead() {
        return age >= 10;
    }

    public void tick() {
        age++;
    }

}
