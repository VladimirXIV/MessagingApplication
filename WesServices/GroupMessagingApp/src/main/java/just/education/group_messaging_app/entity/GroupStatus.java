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
@Table(schema = "group_schema", name = "group_status")
@NoArgsConstructor
@AllArgsConstructor
public class GroupStatus {

    @Id
    @GeneratedValue(generator = "group_status_code_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_status_code_seq", sequenceName = "group_status_code_sequence", schema = "group_schema", allocationSize = 1)
    private Long code;

    private String name;
}