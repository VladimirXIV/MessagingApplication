package just.education.broadcast_messaging_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication(
        scanBasePackages = {
                "just.education.broadcast_messaging_app.config",
                "just.education.broadcast_messaging_app.controller"
        }
)
@EnableConfigurationProperties
public class BroadcastMessagingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BroadcastMessagingApplication.class, args);
    }
}