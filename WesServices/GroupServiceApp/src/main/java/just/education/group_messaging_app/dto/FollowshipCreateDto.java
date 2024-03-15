package just.education.group_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowshipCreateDto {

    private Long groupId;

    private Long userId;
}