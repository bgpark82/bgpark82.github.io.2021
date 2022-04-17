package bgpark.string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {

    @Test
    void intern() {
        String s1 = "문자";
        String s2 = new String("문자");
        String s3 = s2.intern();
        assertThat(s1 == s3).isTrue();
    }
}
