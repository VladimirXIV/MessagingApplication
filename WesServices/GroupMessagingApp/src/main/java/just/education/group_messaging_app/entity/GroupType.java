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


@Getter
@Setter
@Entity
@Table(schema = "group_schema", name = "group_type")
@NoArgsConstructor
@AllArgsConstructor
public class GroupType {

    @Id
    @GeneratedValue(generator = "group_type_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_type_id_seq", sequenceName = "group_type_id_sequence", schema = "group_schema", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}