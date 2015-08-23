package pl.touk.codepot.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lock1 {

    private final Object syn1 = new Object();

    private final Object syn2 = new Object();

    private void firstSecond() throws Exception {

        synchronized (syn1) {
            Thread.sleep(100);
            synchronized (syn2) {
                Thread.sleep(200);
                System.out.println("firstSecond");
            }
        }

    }

    private void secondFirst() throws Exception {

        synchronized (syn2) {
            Thread.sleep(200);
            synchronized (syn1) {
                Thread.sleep(100);
                System.out.println("secondThird");
            }
        }

    }

    public static void main(String[] args) {

        Lock1 lock1 = new Lock1();

        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(() -> {
            while(true) {
                lock1.firstSecond();
            }
        });
        service.submit(() -> {
            while(true) {
                lock1.secondFirst();
            }
        });

    }



}
