package bgpark.float_double;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class RealNumberTest {

    @Test
    void 돈을_계산한다() {
        assertThat(1.03 - 0.42).isEqualTo(0.61);
    }

    @Test
    void name() {
        BigDecimal.valueOf(10);
    }
}
