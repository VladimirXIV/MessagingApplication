package just.education.convo_messaging_app.entity;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@RedisHash
public class SessionData {

    private String wsSessionId;

    @Indexed
    private String participantId;
    private Long userId;
    private boolean isActive;
    private Date createdAt;
    private Date closedAt;


    public SessionData() {
    }

    public SessionData(String wsSessionId, String participantId, Long userId, boolean isActive, Date createdAt, Date closedAt) {
        this.wsSessionId = wsSessionId;
        this.participantId = participantId;
        this.userId = userId;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }


    public String getWsSessionId() {
        return wsSessionId;
    }

    public void setWsSessionId(String wsSessionId) {
        this.wsSessionId = wsSessionId;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }
}