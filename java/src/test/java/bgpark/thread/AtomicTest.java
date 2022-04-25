package bgpark.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private static int count = 0;
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    @Test
    void name() {
        AtomicInteger ai = new AtomicInteger(1);
        ai.incrementAndGet();
    }

    @Test
    void integer() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++;
                    atomicCount.incrementAndGet();
                }
            });
        }
        Thread.sleep(1000);
        System.out.println("atomic 결과 : " + atomicCount.get());
        System.out.println("int 결과 : " + count);
    }
}
