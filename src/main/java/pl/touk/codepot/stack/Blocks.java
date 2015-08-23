package pl.touk.codepot.stack;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.stream.IntStream;

public class Blocks {

    public static void main(String[] args) {
        Blocks blocks = new Blocks();
        blocks.run();
    }

    private void run() {
        IntStream.range(0, 20000).parallel().forEach(i -> {
            Person p = new Person();
            p.setName("person " + i);
            println(p);
        });
    }

    private void println(Person person) {
        try {
            Thread.sleep(1);
            JAXBContext ctx = JAXBContext.newInstance(Person.class);
            ctx.createMarshaller().marshal(person, File.createTempFile("start", "stop"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    private static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
