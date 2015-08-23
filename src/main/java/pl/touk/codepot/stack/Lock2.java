package pl.touk.codepot.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Lock2 {


    private final ReentrantLock syn1 = new ReentrantLock();

    private final ReentrantLock syn2 = new ReentrantLock();

    private void firstSecond() throws Exception {

        syn1.lock();
        Thread.sleep(100);
        syn2.lock();
        Thread.sleep(200);
        syn2.unlock();
        syn1.unlock();

    }

    private void secondFirst() throws Exception {

        syn2.lock();
        Thread.sleep(100);
        syn1.lock();
        Thread.sleep(200);
        syn1.unlock();
        syn2.unlock();

    }

    public static void main(String[] args) {

        Lock2 lock2 = new Lock2();

        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(() -> {
            while (true) {
                lock2.firstSecond();
            }
        });
        service.submit(() -> {
            while (true) {
                lock2.secondFirst();
            }
        });

    }

}
