package just.education.convo_messaging_app.service;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.handler.ConvoMessageHandler;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;


public class ConvoMessageConsumer {

    private ConvoMessageHandler convoMessageHandler;


    public ConvoMessageConsumer(ConvoMessageHandler convoMessageHandler) {
        this.convoMessageHandler = convoMessageHandler;
    }


    @KafkaListener(topics = "${spring.kafka.topic.name}")
    public void receive(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws Exception {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            this.convoMessageHandler.handle((ConvoMessage) message);
        }
    }
}