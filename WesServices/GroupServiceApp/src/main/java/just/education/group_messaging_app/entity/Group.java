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
@Table(schema = "group_schema", name = "group")
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "group_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_sequence", schema = "group_schema", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", table = "group", referencedColumnName = "id")
    private GroupType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_code", table = "group", referencedColumnName = "code")
    private GroupStatus status;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}