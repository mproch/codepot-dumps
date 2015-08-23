package pl.touk.codepot.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lock3 {

    private final Object lock1 = new Object();

    private final Object lock2 = new Object();

    private boolean locked1;

    private boolean locked2;


    private void firstSecond() throws Exception {

        synchronized (lock1) {
            while (locked1) {
                lock1.wait();
            }
            locked1 = true;
        }


        Thread.sleep(100);
        synchronized (lock2) {
            while (locked2) {
                lock2.wait();
            }
            locked2 = true;
        }

        Thread.sleep(200);

        locked1 = false;
        locked2 = false;
        lock2.notifyAll();
        lock1.notifyAll();
    }

    private void secondFirst() throws Exception {


        synchronized (lock2) {
            while (locked2) {
                lock2.wait();
            }
            locked2 = true;
        }
        Thread.sleep(100);
        synchronized (lock1) {
            while (locked1) {
                lock1.wait();
            }
            locked1 = true;
        }
        Thread.sleep(200);

        locked1 = false;
        locked2 = false;
        lock2.notifyAll();
        lock1.notifyAll();
    }

    public static void main(String[] args) {

        Lock3 lock2 = new Lock3();

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
