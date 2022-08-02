package gr.tsitoumis.geasemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class GeaSemiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeaSemiApplication.class, args);
    }

}
