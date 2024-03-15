package just.education.convo_messaging_app.handler;

import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import just.education.convo_messaging_app.entity.SessionData;
import just.education.convo_messaging_app.entity.ConvoMessage;
import just.education.convo_messaging_app.dto.ParticipantReadDto;
import just.education.convo_messaging_app.dto.ConversationReadDto;
import just.education.convo_messaging_app.enums.ConversationAction;
import just.education.convo_messaging_app.service.ParticipantService;
import just.education.convo_messaging_app.service.SessionDataService;
import just.education.convo_messaging_app.service.ConversationService;
import just.education.convo_messaging_app.repository.WebSocketSessionStore;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;


public class ConvoMessageHandler {

    private ObjectMapper objectMapper;
    private SessionDataService sessionDataService;
    private WebSocketSessionStore wsSessionStore;
    private ConversationService conversationService;
    private ParticipantService participantService;


    public ConvoMessageHandler(
            SessionDataService sessionDataService,
            WebSocketSessionStore wsSessionStore,
            ConversationService conversationService,
            ParticipantService participantService
    ) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new ParameterNamesModule());
        this.objectMapper.setVisibility(FIELD, ANY);

        this.sessionDataService = sessionDataService;
        this.wsSessionStore = wsSessionStore;
        this.conversationService = conversationService;
        this.participantService = participantService;
    }


    public void handle(ConvoMessage convoMessage) throws Exception {

        final ConversationAction action = convoMessage.getAction();
        final String recipientId = convoMessage.getRecipientId();

        switch (action) {

            case SEND_MESSAGE_TO_PARTICIPANT -> {

                final SessionData sessionData = sessionDataService.getByParticipantId(recipientId);

                final String wsSessionId = sessionData.getWsSessionId();
                final WebSocketSession wsSession = this.wsSessionStore.getSession(wsSessionId);

                final String msg = objectMapper.writeValueAsString(convoMessage);
                wsSession.sendMessage(new TextMessage(msg));
            }

            case JOIN_CONVERSATION -> {

                //final ConversationReadDto conversation = conversationService.findById(recipientId);
                //final String senderId = convoMessage.getSenderId();


                //conversationService.addParticipant(recipientId, );
            }

            case LEAVE_CONVERSATION -> {

                final ConversationReadDto conversation = conversationService.findById(recipientId);
            }

            case SEND_MESSAGE_TO_CONVERSATION -> {

                final ConversationReadDto conversation = conversationService.findById(recipientId);
                final Set<String> participantIds = conversation.getParticipants()
                        .stream()
                        .map(ParticipantReadDto::getId)
                        .collect(Collectors.toSet());

                final Set<SessionData> sessionsData = new HashSet<>();
                participantIds.forEach(participantId -> sessionsData.add(sessionDataService.getByParticipantId(recipientId)));

                sessionsData.forEach(sessionData -> {

                    final String wsSessionId = sessionData.getWsSessionId();
                    final WebSocketSession wsSession = this.wsSessionStore.getSession(wsSessionId);

                    final String msg;
                    try {
                        msg = objectMapper.writeValueAsString(convoMessage);
                        wsSession.sendMessage(new TextMessage(msg));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    /*
    public void joinToConversation(convoMessage convoMessage) {

       final String participantId = convoMessage.getSenderId();
       final String conversationId = convoMessage.getRecipientId();

       this.participantService.
    }

    public void leave(String conversationName, Long userId) {

        Conversation conversation = conversationManager.getByName(conversationName);
    }


    public void sendToConversationByName(String conversationName, convoMessage convoMessage) throws JsonProcessingException {

        ConversationReadDto conversation = this.conversationService.findByName(conversationName);
        Set<String> recipientIds = conversation.getParticipants()
                .stream()
                .map(ParticipantReadDto::getId)
                .collect(Collectors.toSet());

        String msg = objectMapper.writeValueAsString(convoMessage);

        Set<WebSocketSession> sessions = this.sessionService.getAll(recipientIds);
        sessions.forEach(session ->

                {
                    try {
                        session.sendMessage(new TextMessage(msg));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

        );
    }

    private void sendToConversationById(String recipientId, convoMessage convoMessage) throws Exception {

        ConversationReadDto conversation = this.conversationService.findById(recipientId);
        Set<String> recipientIds = conversation.getParticipants()
                .stream()
                .map(ParticipantReadDto::getId)
                .collect(Collectors.toSet());

        String msg = objectMapper.writeValueAsString(convoMessage);

        Set<WebSocketSession> sessions = this.sessionService.getAll(recipientIds);
        sessions.forEach(session ->

                {
                    try {
                        session.sendMessage(new TextMessage(msg));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

        );
    }

    private void sendToParticipant(String recipientId, convoMessage convoMessage) throws IOException {

        String msg = objectMapper.writeValueAsString(convoMessage);

        WebSocketSession session = this.sessionService.get(recipientId);
        session.sendMessage(new TextMessage(msg));
    }*/
}