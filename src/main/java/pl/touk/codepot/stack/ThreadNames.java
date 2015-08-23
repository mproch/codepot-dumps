package pl.touk.codepot.stack;

import pl.touk.codepot.Sleep;

import java.util.stream.IntStream;

public class ThreadNames {

    public static void main(String[] args) {
        IntStream.range(0, 30).parallel().forEach(i -> wrapWithName("th-"+i, () -> runTask(i)));
    }

    private static int runTask(int count) {
        if (count % 5 == 3) {
            Sleep.sleep(20000);
        }
        return count + 1;
    }

    private static <R, T extends Exception> R wrapWithName(String name, ThrowingRunnable<R, T> action) throws T {
        String currentName = Thread.currentThread().getName();
        try {
            Thread.currentThread().setName(name);
            return action.run();
        } finally {
            Thread.currentThread().setName(currentName);
        }
    }

    interface ThrowingRunnable<R, T extends Exception> {
        R run() throws T;
    }

}
