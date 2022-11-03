package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserPost {

    private final Integer id;

    private final String userId;

    private final String senderId;

    private final String message;

    private final Timestamp createdAt;

    private final Timestamp updatedAt;
}
