package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class User {

    private final Integer id;

    private final String firstName;

    private final String middleName;

    private final String lastName;

    private final String username;

    private final String mobile;

    private final String email;

    private final Timestamp registeredAt;

    private final Timestamp lastLogin;

    private final String intro;

    private final String profileInfo;
}

