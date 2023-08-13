package just.education.broadcast_messaging_app.service;

import just.education.broadcast_messaging_app.model.BroadcastMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Optional;

public class BroadcastMessageConsumerService {

    @KafkaListener(topics = "another-topic")
    public void receive(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();


        }
    }
}
