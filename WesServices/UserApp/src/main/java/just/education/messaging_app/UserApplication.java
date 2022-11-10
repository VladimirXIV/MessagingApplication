package just.education.messaging_app;

import just.education.messaging_app.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;

@Import(value = AppConfig.class)
@SpringBootApplication(scanBasePackages = {
        "just.education.messaging_app.controller"
})
public class UserApplication {

    public static void main(final String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
