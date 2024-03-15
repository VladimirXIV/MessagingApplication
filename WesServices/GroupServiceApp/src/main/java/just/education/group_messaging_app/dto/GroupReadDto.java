package just.education.group_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupReadDto {

    private Long id;

    private String title;

    private String metaTitle;

    private String description;

    private GroupTypeDto type;

    private GroupStatusDto status;

    private Long createdBy;

    private Long updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Timestamp createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Timestamp updatedAt;
}