package just.education.broadcast_messaging_app.model;

public class BroadcastMessage {

    private String messageId;

    private String text;

    public BroadcastMessage(String messageId, String text) {
        this.messageId = messageId;
        this.text = text;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}