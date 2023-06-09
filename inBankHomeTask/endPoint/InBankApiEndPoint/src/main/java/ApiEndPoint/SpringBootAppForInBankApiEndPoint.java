package ApiEndPoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "config"})
public class SpringBootAppForInBankApiEndPoint {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootAppForInBankApiEndPoint.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
        app.run(args);
    }
}