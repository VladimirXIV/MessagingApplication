package just.education.convo_messaging_app.dto;

import java.util.Set;
import java.util.Date;

public class ConversationReadDto {

    private String id;
    private String name;
    private String description;
    private String ownerId;
    private Date createdAt;
    private Set<ParticipantReadDto> participants;
    private Set<String> convoMessageIds;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<ParticipantReadDto> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<ParticipantReadDto> participants) {
        this.participants = participants;
    }

    public Set<String> getconvoMessageIds() {
        return convoMessageIds;
    }

    public void setconvoMessageIds(Set<String> convoMessageIds) {
        this.convoMessageIds = convoMessageIds;
    }
}