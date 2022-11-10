package just.education.messaging_app.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Table(schema = "user_schema", name = "user")
public class User {

    @Id
    private final Long id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "middle_name")
    private final String middleName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "username")
    private final String username;

    @Column(name = "mobile")
    private final String mobile;

    @Column(name = "email")
    private final String email;

    @Column(name = "registered_at")
    private final Timestamp registeredAt;

    @Column(name = "last_login")
    private final Timestamp lastLogin;

    @Column(name = "intro")
    private final String intro;

    @Column(name = "profile_info")
    private final String profileInfo;
}

