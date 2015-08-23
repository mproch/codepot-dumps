package pl.touk.codepot.stack;

import pl.touk.codepot.Sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lock4 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Callable<String> adapter = () -> {
            System.out.println("received");
            return "a";
        };

        Callable<String> service = () -> {
            Sleep.sleep(200);
            return "a" + pool.submit(adapter).get();
        };

        ;
        for (Future<String> f : Stream.of(1, 2, 3).map(i -> pool.submit(service)).collect(Collectors.toList())) {
            System.out.println("got result " + f.get());
        }

    }

}
