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
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;


@Getter
@Setter
@Entity
@Table(schema = "user_schema", name = "friendship_type")
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipType {

    @Id
    @GeneratedValue(generator = "friendship_type_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "friendship_type_id_seq", sequenceName = "friendship_type_id_sequence", schema = "user_schema", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}