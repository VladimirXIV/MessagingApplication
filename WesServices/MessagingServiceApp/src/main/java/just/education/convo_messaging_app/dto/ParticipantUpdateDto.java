package just.education.convo_messaging_app.dto;

public class ParticipantUpdateDto {

    private Long userId;
    private String nickname;


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
}