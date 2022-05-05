package bgpark.equals;

import org.junit.jupiter.api.Test;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualsTest {

    @Test
    void equal() {
        assertThat(new Person("peter")).isEqualTo(new Person("peter"));
    }

    @Test
    void collection() {
        Set<Person> set = new HashSet();
        set.add(new Person("peter"));
        set.add(new Person("peter"));

        assertThat(set.size()).isEqualTo(1);
    }

    @Test
    void hash() {
        int hash1 = new Person("peter").hashCode();
        int hash2 = new Person("peter").hashCode();
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void set() {
        new HashSet<>();
    }

    static class Person {

        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // 주소값이 같으므로 true
            if (o == null) return false; // obj가 null이므로 false
            if (getClass() != o.getClass()) return false; // 클래스의 종류가 다르므로 false

            Person person = (Person) o; // 형변환

            return Objects.equals(name, person.name); // name과 person.name의 주소값이 같거나 값이 같으면 true
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
