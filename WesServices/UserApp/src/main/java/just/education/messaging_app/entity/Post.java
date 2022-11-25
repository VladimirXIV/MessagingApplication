package just.education.messaging_app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(schema = "user_schema", name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(generator = "post_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "post_id_seq",  sequenceName = "post_id_sequence", schema = "user_schema", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", table = "post", referencedColumnName = "id")
    private User user;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(user, post.user) && Objects.equals(senderId, post.senderId) && Objects.equals(message, post.message) && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, senderId, message, createdAt, updatedAt);
    }
}