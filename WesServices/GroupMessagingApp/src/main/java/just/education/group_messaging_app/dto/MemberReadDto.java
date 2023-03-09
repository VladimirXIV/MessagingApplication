package just.education.group_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberReadDto {

    private Long id;

    private Long groupId;

    private Long userId;

    private Long roleId;

    private Long statusCode;

    private String notes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="IST")
    private Timestamp createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="IST")
    private Timestamp updatedAt;
}