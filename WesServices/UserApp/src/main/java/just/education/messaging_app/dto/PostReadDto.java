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
public class PostReadDto {

    private Long id;

    private Long senderId;

    private String message;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
