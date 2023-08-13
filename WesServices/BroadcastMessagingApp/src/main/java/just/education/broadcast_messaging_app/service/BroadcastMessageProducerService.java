package just.education.broadcast_messaging_app.service;

import just.education.broadcast_messaging_app.model.BroadcastMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class BroadcastMessageProducerService {

    public final KafkaTemplate<Object, Object> kafkaTemplate;
    private final String topicName;


    public BroadcastMessageProducerService(KafkaTemplate<Object, Object> kafkaTemplate, String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }


    public void send(BroadcastMessage broadcastMessage) {

        final Message<BroadcastMessage> message = MessageBuilder
                .withPayload(broadcastMessage)
                .setHeader(KafkaHeaders.TOPIC, this.topicName)
                .build();

        this.kafkaTemplate.send(message);
    }
}
