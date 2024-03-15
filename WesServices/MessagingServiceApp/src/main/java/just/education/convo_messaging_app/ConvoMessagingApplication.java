package just.education.convo_messaging_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication(
        scanBasePackages = {
                "just.education.convo_messaging_app.config"
        }
)
@EnableConfigurationProperties
public class ConvoMessagingApplication {

    public static void main(final String[] args) {

        try {
            SpringApplication.run(ConvoMessagingApplication.class, args);
        } catch (Exception e) {

            System.out.println(e.getStackTrace());
        }

    }
}