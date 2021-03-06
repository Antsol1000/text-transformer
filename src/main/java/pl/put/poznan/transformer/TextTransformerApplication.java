package pl.put.poznan.transformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer"})
public class TextTransformerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
