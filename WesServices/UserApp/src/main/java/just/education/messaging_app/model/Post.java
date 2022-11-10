package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Table(schema = "user_schema", name = "post")
public class Post {

    @Id
    private final Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", table = "user", name = "id")
    private final User user;

    @Column(name = "sender_id")
    private final String senderId;

    @Column(name = "message")
    private final String message;

    @Column(name = "ceated_at")
    private final Timestamp createdAt;

    @Column(name = "updated_at")
    private final Timestamp updatedAt;
}
