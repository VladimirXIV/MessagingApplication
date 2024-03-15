package just.education.convo_messaging_app.handler;

import java.util.Date;
import java.time.Instant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import just.education.convo_messaging_app.entity.SessionData;
import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.dto.ParticipantReadDto;
import just.education.convo_messaging_app.dto.ParticipantCreateDto;
import just.education.convo_messaging_app.service.ParticipantService;
import just.education.convo_messaging_app.service.SessionDataService;
import just.education.convo_messaging_app.service.ConvoMessageProducer;
import just.education.convo_messaging_app.repository.WebSocketSessionStore;


public class ConvoMessageWebSocketHandler extends TextWebSocketHandler {

    private final ConvoMessageProducer convoMessageProducer;
    private final ParticipantService participantService;
    private final WebSocketSessionStore wsSessionStore;
    private final SessionDataService sessionDataService;


    public ConvoMessageWebSocketHandler(
            ConvoMessageProducer convoMessageProducer,
            ParticipantService participantService,
            WebSocketSessionStore wsSessionStore,
            SessionDataService sessionDataService
    ) {
        this.convoMessageProducer = convoMessageProducer;
        this.participantService = participantService;
        this.wsSessionStore = wsSessionStore;
        this.sessionDataService = sessionDataService;
    }


    @Override
    public void afterConnectionEstablished(final WebSocketSession wsSession) throws Exception {

        final String wsSessionId = wsSession.getId();

        // persist new ws-session
        this.wsSessionStore.addSession(wsSessionId, wsSession);

        // persist new participant
        final ParticipantCreateDto newParticipant = (ParticipantCreateDto) wsSession.getAttributes().remove("participant");
        ParticipantReadDto createdParticipant = this.participantService.create(newParticipant);

        // persist ws-session data
        final SessionData sessionData = new SessionData(
                wsSessionId,
                createdParticipant.getId(),
                createdParticipant.getUserId(),
                Boolean.TRUE,
                Date.from(Instant.now()),
                null
        );
        this.sessionDataService.put(sessionData);

        // answer the client
        //final String jsonPayload = this.serialize(createdParticipant);
        //final TextMessage message = new TextMessage(jsonPayload);
        wsSession.sendMessage(new TextMessage("message"));
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession wsSession, final CloseStatus status) throws Exception {

        final String wsSessionId = wsSession.getId();

        this.wsSessionStore.removeSession(wsSessionId); // delete this ws-session
        final SessionData sessionData = this.sessionDataService.delete(wsSessionId);  // delete session data

        // delete participant
        final String participantId = sessionData.getParticipantId();
        this.participantService.deleteById(participantId);
    }

    @Override
    public void handleTextMessage(final WebSocketSession wsSession, final TextMessage textMessage) {

        final String jsonPayload = textMessage.getPayload();
        final ConvoMessage convoMessage = this.deserialize(jsonPayload, ConvoMessage.class);

        this.convoMessageProducer.send(convoMessage);
    }

    private String serialize(final Object object) {

        try {

            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new ParameterNamesModule());
            mapper.setVisibility(FIELD, ANY);

            return mapper.writeValueAsString(object);

        } catch (final JsonProcessingException e) {

            String responseString = "<cannot serialize response, check logs for error>";
            return null;
            //log.warn("Cannot serialize activity object for exchange_log: '{}'", activityObjectDTO, e);
        }
    }

    public <T> T deserialize(final String stringValue, Class<T> valueType) {
        try {

            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stringValue, valueType);
        } catch (final Exception e) {
            //log.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}