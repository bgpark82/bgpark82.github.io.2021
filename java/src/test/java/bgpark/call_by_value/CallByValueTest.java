package bgpark.call_by_value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CallByValueTest {

    @Test
    void callByReference() {
        int x = 1;
        int[] y = {1, 2, 3};
        Person person1 = new Person("개발자");
        Person person2 = new Person("기획자");

        process(x, y, person1, person2);

        assertThat(x).isEqualTo(1);
        assertThat(y[0]).isEqualTo(2);
        assertThat(person1.getName()).isEqualTo("개발자");
        assertThat(person2.getName()).isEqualTo("새로운 기획자");
    }

    private void process(int x, int[] y, Person person1, Person person2) {
        x++;
        y[0]++;
        person1 = new Person("새로운 개발자");
        person2.setName("새로운 기획자");
    }
}
