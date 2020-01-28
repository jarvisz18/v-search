package cn.ixan.search.controller;

import com.google.common.base.CharMatcher;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TikaTest {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\规范.docx");
        Tika tika = new Tika();

        try {
            System.out.println(tika.detect(file));
            System.out.println(tika.parseToString(file));
            System.out.println("元数据-----");
            Metadata metadata = new Metadata();

            new AutoDetectParser().parse(new FileInputStream(file),new BodyContentHandler(),metadata,new ParseContext());
            String[] names = metadata.names();
            for(String name:names){
                System.out.println(name + ":" + metadata.get(name));
            }
        } catch (IOException | TikaException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParsePdf(){
        Tika tika = new Tika();
        File file = new File("C:\\Users\\Administrator\\Desktop\\网络泡沫.pdf");
        String s = null;
        try {
            s = tika.parseToString(file);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
        }
        String from = CharMatcher.whitespace().removeFrom(s);
        System.out.println(from);
    }


}
