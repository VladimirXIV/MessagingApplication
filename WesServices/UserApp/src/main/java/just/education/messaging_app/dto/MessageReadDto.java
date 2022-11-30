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
public class MessageReadDto {

    private Long id;

    private Long senderId;

    private Long receiverId;

    private String text;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}