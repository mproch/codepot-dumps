package pl.touk.codepot.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leak0 {

    private List<Request> longList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Leak0 leak = new Leak0();

        for (int i=0;;i++) {
            Thread.sleep(1);
            leak.longList.add(new Request(IntStream.range(0, i).mapToObj(Integer::toString).collect(Collectors.joining(""))));
        }

    }


    private static class Request {
        private String value;

        public Request(String value) {
            this.value = value;
        }
    }
}
