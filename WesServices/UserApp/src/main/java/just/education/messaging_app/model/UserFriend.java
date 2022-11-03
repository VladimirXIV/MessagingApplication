package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserFriend {

    private final Integer id;

    private final String sourceId;

    private final String targetId;

    private final String type;

    private final String status;

    private final Timestamp createdAt;

    private final Timestamp updatedAt;

    private final String notes;
}
