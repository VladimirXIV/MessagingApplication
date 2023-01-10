package just.education.group_messaging_app.entity;

import lombok.Setter;
import lombok.Getter;
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
@Table(schema = "group_schema", name = "follower")
@NoArgsConstructor
@AllArgsConstructor
public class Follower {

    @Id
    @GeneratedValue(generator = "follower_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "follower_id_seq", sequenceName = "follower_id_sequence", schema = "group_schema", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", table = "message", referencedColumnName = "id")
    private Group group;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}