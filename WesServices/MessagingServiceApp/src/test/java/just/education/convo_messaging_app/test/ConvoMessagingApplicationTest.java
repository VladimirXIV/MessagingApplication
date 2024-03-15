package just.education.convo_messaging_app.test;

import just.education.convo_messaging_app.dto.ParticipantCreateDto;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConvoMessagingApplicationTest {

    static final String URI = "ws://localhost:9060/messaging";


    @Before
    public void beforeEachSetup(){
    }

    @Test
    public void sendMessage() throws Exception {

        final ParticipantCreateDto newParticipant = new ParticipantCreateDto(Long.valueOf(17456L), "till_lorenz_75");

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketSession webSocketSession = webSocketClient.execute(

                new TextWebSocketHandler() {
                    @Override
                    public void handleTextMessage(WebSocketSession session, TextMessage message) {
                        System.out.println("received message - " + message.getPayload());
                    }

                    @Override
                    public void afterConnectionEstablished(WebSocketSession session) {
                        System.out.println("established connection - " + session);
                        session.getAttributes().put("participant", newParticipant);
                    }
                },
                URI,
                new WebSocketHttpHeaders()
        ).get();
    }
}