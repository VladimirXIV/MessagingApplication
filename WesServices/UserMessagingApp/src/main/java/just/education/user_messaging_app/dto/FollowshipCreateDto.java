package just.education.user_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowshipCreateDto {

    private Long followedId;

    private Long typeId;

    private String notes;
}
