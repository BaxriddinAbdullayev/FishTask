package lesson.uz.test;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Test{

    private String name;
    private int age;

    public Test(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void kopayish(){
        Test test = Main.tests.get(0);
        if(this.equals(test)){
            System.out.println("dfdksfjd");
        }
        System.out.println("sdklfjsfdsj");
    }
}
