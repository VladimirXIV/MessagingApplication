package just.education.convo_messaging_app.service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;

import just.education.convo_messaging_app.entity.ConvoMessage;


public class ConvoMessageProducer {

    private final KafkaTemplate<Object, ConvoMessage> kafkaTemplate;
    private final String topic;


    public ConvoMessageProducer(
            KafkaTemplate<Object, ConvoMessage> kafkaTemplate,
            @Value("${spring.kafka.producer.topic}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(ConvoMessage message) {

        final CompletableFuture<SendResult<Object, ConvoMessage>> completableFuture = kafkaTemplate.send(this.topic, message);
        completableFuture.whenComplete((msg, exception) -> {
            if (Objects.nonNull(exception)) {
                System.out.printf("Exception occurred: %s", List.of(exception));
            } else {
                System.out.printf("It is all right: %s", List.of(msg));
            }
        });
    }
}