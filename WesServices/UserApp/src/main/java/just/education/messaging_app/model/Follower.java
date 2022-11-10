package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Table(schema = "user_schema", name = "follower")
public class Follower {

    @Id
    private final Long id;

    @Column(name = "source_id")
    private final String sourceId;

    @Column(name = "target_id")
    private final String targetId;

    @Column(name = "type")
    private final String type;

    @Column(name = "created_at")
    private final Timestamp createdAt;

    @Column(name = "updated_at")
    private final Timestamp updatedAt;
}
