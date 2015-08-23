package pl.touk.codepot.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leak1 {

    private List<String> longList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Leak1 leak = new Leak1();

        for (int i=0;;i++) {
            Thread.sleep(1);
            leak.longList.add(IntStream.range(0, i).mapToObj(Integer::toString).collect(Collectors.joining("")));
        }

    }
}
