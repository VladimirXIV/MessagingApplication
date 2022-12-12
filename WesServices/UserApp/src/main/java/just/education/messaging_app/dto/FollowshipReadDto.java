package just.education.messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowshipReadDto {

    private Long id;

    private Long followerId;

    private Long followedId;

    private FriendshipTypeReadDto type;

    private String notes;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}