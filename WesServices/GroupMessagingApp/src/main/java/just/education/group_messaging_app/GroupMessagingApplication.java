package just.education.group_messaging_app;

import just.education.group_messaging_app.config.AppConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(value = AppConfig.class)
@SpringBootApplication(scanBasePackages = {
        "just.education.group_messaging_app.controller"
})
public class GroupMessagingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(GroupMessagingApplication.class, args);
    }
}