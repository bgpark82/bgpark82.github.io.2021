package bgpark.stringbuilder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringBuilderTest {

    @Test
    void builder() {
        StringBuilder sb = new StringBuilder();
        sb.append("이름");
    }

    @Test
    void buffer() {
        StringBuffer sb = new StringBuffer();
        sb.append("이름");
    }

    @Test
    void string() {
        String a = "abc";
        System.out.println(Integer.toHexString(a.hashCode()));
        a += "def";
        System.out.println(Integer.toHexString(a.hashCode()));
        a += "ghi";
        System.out.println(Integer.toHexString(a.hashCode()));
    }
}
