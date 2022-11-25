package just.education.messaging_app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String firstName;

    private String middleName;

    private String lastName;

    private String username;

    private String mobile;

    private String email;

    private String intro;

    private String profileInfo;
}