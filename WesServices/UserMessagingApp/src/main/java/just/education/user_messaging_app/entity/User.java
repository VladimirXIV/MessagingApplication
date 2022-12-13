package just.education.user_messaging_app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import java.sql.Timestamp;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(schema = "user_schema", name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_sequence", schema = "user_schema", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "intro")
    private String intro;

    @Column(name = "profile_info")
    private String profileInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(mobile, user.mobile) && Objects.equals(email, user.email) && Objects.equals(registeredAt, user.registeredAt) && Objects.equals(lastLogin, user.lastLogin) && Objects.equals(intro, user.intro) && Objects.equals(profileInfo, user.profileInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, username, mobile, email, registeredAt, lastLogin, intro, profileInfo);
    }
}