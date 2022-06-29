package gr.tsitoumis.geasemi.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class commandsTest {
    @Test
    public void gitClone() {
        try {
            Commands.gitClone("https://github.com/dinosts/dinosts.github.io");

        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }
}
