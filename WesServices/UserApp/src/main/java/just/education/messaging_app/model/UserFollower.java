package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserFollower {

    private final Integer id;

    private final String sourceId;

    private final String targetId;

    private final String type;

    private final Timestamp createdAt;

    private final Timestamp updatedAt;
}
