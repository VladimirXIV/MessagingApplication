package just.education.user_messaging_app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(schema = "user_schema", name = "friendship")
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(generator = "friendship_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "friendship_id_seq", sequenceName = "friendship_id_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", table = "friendship", referencedColumnName = "id")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", table = "friendship", referencedColumnName = "id")
    private User receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", table = "friendship", referencedColumnName = "id")
    private FriendshipType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_code", table = "friendship", referencedColumnName = "code")
    private FriendshipStatus status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}