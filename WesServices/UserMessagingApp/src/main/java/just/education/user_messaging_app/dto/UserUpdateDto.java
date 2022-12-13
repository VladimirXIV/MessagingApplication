package just.education.user_messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private String firstName;

    private String middleName;

    private String lastName;

    private String username;

    private String mobile;

    private String email;

    private String intro;

    private String profileInfo;
}