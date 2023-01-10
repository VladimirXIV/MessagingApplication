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
public class MemberCreateDto {

    private Long groupId;

    private Long userId;

    private Long roleId;

    private Long statusCode;

    private String notes;
}