package just.education.user_messaging_app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;


@Getter
@Setter
@Entity
@Table(schema = "user_schema", name = "friendship_status")
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipStatus {

    @Id
    @GeneratedValue(generator = "friendship_status_code_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "friendship_status_code_seq", sequenceName = "friendship_status_code_sequence", schema = "user_schema", allocationSize = 1)
    private Long code;

    @Column(name = "name")
    private String name;
}