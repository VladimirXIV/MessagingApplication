package just.education.user_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipReadDto {

    private Long id;

    private Long senderId;

    private Long receiverId;

    private FriendshipTypeReadDto type;

    private FriendshipStatusReadDto status;

    private String notes;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
