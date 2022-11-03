package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserMessage {

    private final Integer id;

    private final String sourceId;

    private final String targetId;

    private final String message;

    private final Timestamp createdAt;

    private final Timestamp updatedAt;
}
