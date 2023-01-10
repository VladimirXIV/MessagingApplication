package just.education.group_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupReadDto {

    private Long id;

    private String title;

    private String metaTitle;

    private String summary;

    private GroupStatusDto status;

    private String info;

    private Long createdBy;

    private Long updatedBy;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}