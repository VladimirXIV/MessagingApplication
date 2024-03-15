package just.education.convo_messaging_app.entity;

import java.util.Set;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document(collection = "participant")
public class Participant {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    private Long userId;
    private String nickname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date enteredAt;
    @Field(targetType = FieldType.OBJECT_ID)
    private Set<String> convoMessageIds;
    @DBRef(lazy = true)
    private Set<Conversation> conversations;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getEnteredAt() {
        return enteredAt;
    }

    public void setEnteredAt(Date enteredAt) {
        this.enteredAt = enteredAt;
    }

    public Set<String> getconvoMessageIds() {
        return convoMessageIds;
    }

    public void setconvoMessageIds(Set<String> convoMessageIds) {
        this.convoMessageIds = convoMessageIds;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }
}