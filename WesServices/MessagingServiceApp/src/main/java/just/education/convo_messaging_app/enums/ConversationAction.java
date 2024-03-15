package just.education.convo_messaging_app.enums;

public enum ConversationAction {

    JOIN_CONVERSATION("JOIN_CONVERSATION"),
    LEAVE_CONVERSATION("LEAVE_CONVERSATION"),
    SEND_MESSAGE_TO_CONVERSATION("SEND_MESSAGE_TO_CONVERSATION"),
    SEND_MESSAGE_TO_PARTICIPANT("SEND_MESSAGE_TO_PARTICIPANT");

    private final String actionName;


    ConversationAction(String actionName) {
        this.actionName = actionName;
    }


    public String getActionName() {
        return actionName;
    }
}