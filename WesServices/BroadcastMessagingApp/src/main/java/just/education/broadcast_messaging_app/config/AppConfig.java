package just.education.broadcast_messaging_app.config;

import just.education.broadcast_messaging_app.service.BroadcastMessageProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;


@Configuration
public class AppConfig {

    @Bean
    public BroadcastMessageProducerService broadcastMessageProducerService(
            KafkaTemplate<Object, Object> kafkaTemplate,
            @Value("${app.topic.example}") String topicName
    ) {

        return new BroadcastMessageProducerService(kafkaTemplate, topicName);
    }
}