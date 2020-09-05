import org.junit.Test;

import java.io.IOException;
import java.lang.module.Configuration;
import java.util.Properties;

public class SomeTest {

    @Test
    public void someTest() {
        Properties properties = new Properties();
        Configuration configuration;
        try {
            properties.load(getClass().getResourceAsStream("some.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.list(System.out);
        System.out.println(properties.get("hello"));
        System.out.println(properties.get("word"));
        System.out.println(properties.get("world"));

        System.getProperties().list(System.out);
        System.getenv().forEach((s, s2) -> System.out.println(s + "; " + s2));
    }
}
