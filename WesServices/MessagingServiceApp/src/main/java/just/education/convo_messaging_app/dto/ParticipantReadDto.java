package just.education.convo_messaging_app.dto;

import java.util.Date;
import java.util.Set;

public class ParticipantReadDto {

    private String id;
    private Long userId;
    private String nickname;
    private Date enteredAt;
    private Set<String> convoMessageIds;
    private Set<ConversationReadDto> conversations;


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

    public Set<ConversationReadDto> getConversations() {
        return conversations;
    }

    public void setConversations(Set<ConversationReadDto> conversations) {
        this.conversations = conversations;
    }
}