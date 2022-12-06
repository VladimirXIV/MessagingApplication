package just.education.messaging_app.entity;

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
@Table(schema = "user_schema", name = "followship")
@NoArgsConstructor
@AllArgsConstructor
public class Followship {

    @Id
    @GeneratedValue(generator = "followship_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "followship_id_seq", sequenceName = "followship_id_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id", table = "followship", referencedColumnName = "id")
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followed_id", table = "followship", referencedColumnName = "id")
    private User followed;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}