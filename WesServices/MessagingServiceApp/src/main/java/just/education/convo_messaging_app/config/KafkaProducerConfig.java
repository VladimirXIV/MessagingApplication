package just.education.convo_messaging_app.config;

import java.util.Map;
import java.util.HashMap;

import just.education.convo_messaging_app.entity.ConvoMessage;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class KafkaProducerConfig {

    @Bean
    public Map<String, Object> producerConfigs(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers
    ) {

        final Map<String, Object> producerConfigs = new HashMap<>();
        producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return producerConfigs;
    }

    @Bean
    public ProducerFactory<Object, ConvoMessage> producerFactory(
            @Qualifier(value = "producerConfigs") Map<String, Object> producerConfigs
    ) {

        return new DefaultKafkaProducerFactory<>(producerConfigs);
    }

    @Bean
    public KafkaTemplate<Object, ConvoMessage> kafkaTemplate(ProducerFactory<Object, ConvoMessage> producerFactory){

        return new KafkaTemplate<>(producerFactory);
    }
}