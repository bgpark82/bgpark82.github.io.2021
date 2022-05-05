package bgpark.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class VolatileTest {

    private static int count = 0;

    @Test
    void test() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
//            es.execute(() -> Person.add());
            es.execute(() -> Person.atomicAdd());
        }
    }

    private static volatile boolean stop = false;
//    private static volatile int count = 50;

    @Test
    void test2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            while (!stop) {

            }
        });
        Thread.sleep(1000);
        stop = true;
    }

    @Test
    void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executorService.submit(() -> {
                while (!stop) {}
                System.out.println("count=" + --count);
            });
        }
        Thread.sleep(1000);
        stop = true;
        Thread.sleep(1000);
        assertThat(count).isEqualTo(0);
    }

    @Test
    void test4() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int j = 0; j < 2; j++) {
            es.submit(() -> {
                for (int i = 0; i < 100; i++) {
                    count++;
                }
            });
        }
        Thread.sleep(2000);
        assertThat(count).isEqualTo(200);
    }

    static class Person {

        static volatile int i = 0, j = 0;

        static AtomicInteger aI = new AtomicInteger(0);
        static AtomicInteger aJ = new AtomicInteger(0);

        static void add() {
            i++; j++;
            System.out.println("i=" + i + ", j=" + j);
        }

        static void atomicAdd() {
            aI.incrementAndGet();
            aJ.incrementAndGet();
            System.out.println("i=" + aI.get()+ ", j=" + aJ.get());
        }
    }
}
