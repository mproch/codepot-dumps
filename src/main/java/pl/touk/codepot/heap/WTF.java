package pl.touk.codepot.heap;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class WTF {

    public static void main(String[] args) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();

        Transformer transformer = factory.newTransformer(new StreamSource(
                WTF.class.getResourceAsStream("/trans.xslt")));
        StringWriter writer = new StringWriter();
        transformer.transform(new StreamSource(new StringReader("<aa/>")), new StreamResult(writer));

        Thread.sleep(200000);

        System.out.println(writer);
    }

}
