package just.education.broadcast_messaging_app.config;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import just.education.broadcast_messaging_app.model.BroadcastMessage;


@Configuration
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> consumerConfigs(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${spring.kafka.consumer.group-id}") String groupId
    ) {

        final Map<String,Object> consumerConfigs = new HashMap<>();
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerConfigs.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        consumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        consumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        consumerConfigs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return consumerConfigs;
    }

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory(Map<String,Object> consumerConfigs) {

        return new DefaultKafkaConsumerFactory<>(consumerConfigs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConsumerFactory<Object, Object> consumerFactory
    ) {

        final ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}