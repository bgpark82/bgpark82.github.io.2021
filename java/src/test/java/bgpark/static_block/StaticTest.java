package bgpark.static_block;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StaticTest {

    @Test
    void outOfMemory() {
        for (int i = 0; i < 10; i++) {
            new StaticBlock();
        }
    }

    @Test
    void classVariable() {
        Person p1 = new Person();
        Person p2 = new Person();
        assertThat(p1.name.hashCode() == p2.name.hashCode()).isTrue();
    }

    @Test
    void staticFinal() {
        System.out.println(Car.name);
    }

    static class Person {

        public static String name = "name";
    }

    static class Car {

        static final String name;

        static {
            name = "car";
        }
    }
}
