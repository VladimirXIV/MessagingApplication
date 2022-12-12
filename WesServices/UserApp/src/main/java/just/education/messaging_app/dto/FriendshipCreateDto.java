package just.education.messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipCreateDto {

    private Long receiverId;

    private Long typeId;

    private Long statusCode;

    private String notes;
}