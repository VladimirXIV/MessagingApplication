package just.education.group_messaging_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "just.education.group_messaging_app.config",
        "just.education.group_messaging_app.controller"
})
public class GroupMessagingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(GroupMessagingApplication.class, args);
    }
}