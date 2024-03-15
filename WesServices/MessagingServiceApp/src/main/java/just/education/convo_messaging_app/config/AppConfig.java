package just.education.convo_messaging_app.config;

import just.education.convo_messaging_app.cache.SessionDataCache;
import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.handler.ConvoMessageHandler;
import just.education.convo_messaging_app.handler.ConvoMessageWebSocketHandler;
import just.education.convo_messaging_app.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import just.education.convo_messaging_app.mapper.ParticipantMapper;
import just.education.convo_messaging_app.mapper.ConversationMapper;
import just.education.convo_messaging_app.mapper.ConvoMessageMapper;

import just.education.convo_messaging_app.repository.WebSocketSessionStore;
import just.education.convo_messaging_app.repository.ParticipantRepository;
import just.education.convo_messaging_app.repository.ConversationRepository;
import just.education.convo_messaging_app.repository.ConvoMessageRepository;


@Configuration
public class AppConfig {

    @Bean
    public ParticipantMapper participantMapper() {

        return ParticipantMapper.INSTANCE;
    }

    @Bean
    public ConversationMapper conversationMapper() {

        return ConversationMapper.INSTANCE;
    }

    public ConvoMessageMapper convoMessageMapper() {

        return ConvoMessageMapper.INSTANCE;
    }

    @Bean
    public ParticipantRepository participantRepository(MongoTemplate mongoTemplate) {

        return new ParticipantRepository(mongoTemplate);
    }

    @Bean
    public ConversationRepository conversationRepository(MongoTemplate mongoTemplate) {

        return new ConversationRepository(mongoTemplate);
    }

    @Bean
    public ConvoMessageRepository convoMessageRepository(MongoTemplate mongoTemplate) {

        return new ConvoMessageRepository(mongoTemplate);
    }

    @Bean
    public SessionDataService sessionDataService(SessionDataCache sessionDataCache) {

        return new SessionDataService(sessionDataCache);
    }

    @Bean
    public WebSocketSessionStore webSocketSessionStore(MongoTemplate mongoTemplate) {

        return WebSocketSessionStore.getInstance();
    }

   @Bean
   public ParticipantService participantService(ParticipantRepository participantRepository) {

        return new ParticipantService(participantRepository, this.participantMapper());
   }

   @Bean
   public ConversationService conversationService(ConversationRepository conversationRepository) {

        return new ConversationService(conversationRepository, this.conversationMapper());
   }

   @Bean
   public ConvoMessageService convoMessageService(ConvoMessageRepository convoMessageRepository) {

        return new ConvoMessageService(convoMessageRepository, this.convoMessageMapper());
   }

    @Bean
    public ConvoMessageHandler convoMessageHandler(
            SessionDataService sessionDataService,
            WebSocketSessionStore webSocketSessionStore,
            ConversationService conversationService,
            ParticipantService participantService
    ) {

        return new ConvoMessageHandler(sessionDataService, webSocketSessionStore, conversationService, participantService);
    }

    @Bean
    public ConvoMessageProducer convoMessageProducer(
            KafkaTemplate<Object, ConvoMessage> kafkaTemplate,
            @Value(value = "${spring.kafka.topic.name}") String topicName
    ) {

        return new ConvoMessageProducer(kafkaTemplate, topicName);
    }

    @Bean
    public ConvoMessageWebSocketHandler convoMessageWebSocketHandler(
            ConvoMessageProducer convoMessageProducer,
            ParticipantService participantService,
            WebSocketSessionStore wsSessionStore,
            SessionDataService sessionDataService
    ) {

        return new ConvoMessageWebSocketHandler(convoMessageProducer, participantService, wsSessionStore, sessionDataService);
    }
}