package just.education.security_messaging_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication(
        scanBasePackages = {
                "just.education.security_messaging_app.config",
                "just.education.security_messaging_app.security",
                "just.education.security_messaging_app.controller",
        }
)
@EnableConfigurationProperties
public class SecurityMessagingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SecurityMessagingApplication.class, args);
    }
}