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
public class PostReadDto {

    private Long id;

    private Long groupId;

    private Long userId;

    private String text;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
