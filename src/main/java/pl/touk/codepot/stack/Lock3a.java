package pl.touk.codepot.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock3a {

    private final Object syn1 = new Object();

    private final ReentrantReadWriteLock syn2 = new ReentrantReadWriteLock();

    private void firstSecond() throws Exception {

        synchronized (syn1) {

            Thread.sleep(200);
            syn2.writeLock().lock();
            try {
                Thread.sleep(100);
            } finally {
                syn2.writeLock().unlock();
            }
            Thread.sleep(100);
        }
    }

    private void secondFirst() throws Exception {
        syn2.readLock().lock();
        Thread.sleep(100);

        try {
            synchronized (syn1) {
                Thread.sleep(200);
            }
        } finally {
            syn2.readLock().unlock();
        }


    }

    public static void main(String[] args) {

        Lock3a lock3a = new Lock3a();

        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(() -> {
            while (true) {
                lock3a.firstSecond();
                System.out.println("end one");
            }
        });
        service.submit(() -> {
            while (true) {
                lock3a.secondFirst();
                System.out.println("end two");

            }
        });

    }

}
