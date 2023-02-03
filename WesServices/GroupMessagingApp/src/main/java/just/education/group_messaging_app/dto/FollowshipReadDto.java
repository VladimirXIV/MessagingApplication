package just.education.group_messaging_app.dto;

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

    private Long userId;

    private Long groupId;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}