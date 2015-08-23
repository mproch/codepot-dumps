package pl.touk.codepot.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Sampler {

    public static final Logger LOG = LoggerFactory.getLogger(Sampler.class);

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        for (int i=0; i< 10000000; i++) {
            list.add(i);
            LOG.trace("Current list is " + list);
        }

    }

}
