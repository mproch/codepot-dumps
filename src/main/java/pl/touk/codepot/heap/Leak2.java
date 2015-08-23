package pl.touk.codepot.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leak2 {


    public static void main(String[] args) throws InterruptedException {

        List<String> longList = new ArrayList<>();

        for (int i = 0; i < Integer.MAX_VALUE - 1; i++) {
            Thread.sleep(1);
            longList.add(IntStream.range(0, i).mapToObj(Integer::toString).collect(Collectors.joining("")));
        }

        System.out.println(longList);

    }

}
