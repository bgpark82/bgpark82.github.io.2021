package bgpark.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RunnableTest {

    @Test
    void run() {
        Thread thread1 = new Thread(() -> System.out.println("hello"));
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        thread1.start();
        thread2.start();
    }

    @Test
    void callable() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task("hello"));
        assertThat(future.isDone()).isFalse();
        assertThat(future.get()).isEqualTo("hello");
        assertThat(future.isDone()).isTrue();
    }

    @Test
    void exception() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task("world"));
        assertThatThrownBy(() -> future.get()).isInstanceOf(ExecutionException.class);
    }

    @Test
    void future() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1500);
                return "hello";
            }
        });
        assertThatThrownBy(() -> future.get(1, TimeUnit.SECONDS)).isInstanceOf(TimeoutException.class);
    }

    static class Task implements Callable<String> {

        private String message;

        public Task(String message) {
            this.message = message;
        }

        @Override
        public String call() throws Exception {
            if (!message.equals("hello")) {
                throw new RuntimeException(message + " is invalid");
            }
            return message;
        }
    }
}
