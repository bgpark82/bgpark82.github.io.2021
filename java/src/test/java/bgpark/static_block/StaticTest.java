package bgpark.static_block;

import org.junit.jupiter.api.Test;

public class StaticTest {

    @Test
    void outOfMemory() {
        for (int i = 0; i < 10; i++) {
            new StaticBlock();
        }
    }
}
