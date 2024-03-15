package just.education.convo_messaging_app.websocket;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import just.education.convo_messaging_app.entity.Conversation;


public class ConversationManager {

    private ConcurrentMap<String, Conversation> conversations;


    public ConversationManager() {

        this.conversations = new ConcurrentHashMap<>();
    }


    public Conversation add(Conversation conversation) {

        String conversationId = conversation.getId();

        return this.conversations.put(conversationId, conversation);
    }

    public Conversation remove(String conversationId) {

        return this.conversations.remove(conversationId);
    }

    public Conversation getById(String conversationId) {

        return this.conversations.get(conversationId);
    }

    public Conversation getByName(String conversationName) {

        Optional<Conversation> conversationOpt = this.conversations.values()
                .stream()
                .filter(conversation -> conversation.getName().equals(conversationName))
                .findFirst();

       if (conversationOpt.isPresent()) {
           return conversationOpt.get();
       }

       return null;
    }

    public Collection<Conversation> getAll() {

        return this.conversations.values();
    }
}