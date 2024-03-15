package just.education.convo_messaging_app.config;

import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.repository.WebSocketSessionStore;
import just.education.convo_messaging_app.service.ConvoMessageProducer;
import just.education.convo_messaging_app.service.ParticipantService;
import just.education.convo_messaging_app.service.SessionDataService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import just.education.convo_messaging_app.handler.CustomHandshakeHandler;
import just.education.convo_messaging_app.handler.ConvoMessageWebSocketHandler;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ConvoMessageWebSocketHandler convoMessageWebSocketHandler;


    @Autowired
    public WebSocketConfig(ConvoMessageWebSocketHandler convoMessageWebSocketHandler) {
        this.convoMessageWebSocketHandler = convoMessageWebSocketHandler;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        webSocketHandlerRegistry
                .addHandler(this.convoMessageWebSocketHandler, "/messaging").setAllowedOrigins("*")
                .setHandshakeHandler(new CustomHandshakeHandler());
    }
}