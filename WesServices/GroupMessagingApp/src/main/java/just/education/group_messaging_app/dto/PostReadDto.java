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
public class PostReadDto {

    private Long id;

    private Long groupId;

    private Long userId;

    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp updatedAt;
}
