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
public class GroupUpdateDto {

    private String title;

    private String metaTitle;

    private String summary;

    private Long statusCode;

    private String info;
}