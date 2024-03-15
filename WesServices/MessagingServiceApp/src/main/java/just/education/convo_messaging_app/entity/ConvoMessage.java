package just.education.convo_messaging_app.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import just.education.convo_messaging_app.enums.ConversationAction;


@Document(collection = "convo_message")
public class ConvoMessage {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    @DBRef
    private Conversation conversation;
    @Field(targetType = FieldType.OBJECT_ID)
    private String senderId;
    @Field(targetType = FieldType.OBJECT_ID)
    private String recipientId;
    private String text;
    private ConversationAction action;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createdAt;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ConversationAction getAction() {
        return action;
    }

    public void setAction(ConversationAction action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}