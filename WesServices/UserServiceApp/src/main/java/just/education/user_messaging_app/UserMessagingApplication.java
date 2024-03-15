package just.education.user_messaging_app;

import just.education.user_messaging_app.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



@SpringBootApplication(scanBasePackages = {
        "just.education.user_messaging_app.config",
        "just.education.user_messaging_app.controller"
})
public class UserMessagingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(UserMessagingApplication.class, args);
    }
}
