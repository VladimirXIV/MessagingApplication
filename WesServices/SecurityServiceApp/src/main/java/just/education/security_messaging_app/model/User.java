package just.education.security_messaging_app.model;

import java.util.Set;
import java.util.UUID;
import java.util.List;
import java.util.Objects;
import java.util.HashSet;
import java.util.Collection;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user")
public class User {

    @Id
    private UUID id;
    private String email;
    private String username;
    private String password;
    @DBRef
    private Set<Role> roles;
    @DBRef
    private RefreshToken refreshToken;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(String username, String email, String password) {

        this.roles = new HashSet<>();

        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<? extends GrantedAuthority> getAuthorities() {

        return this.getRoles()
                .stream()
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(email, user.email)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, username, password, roles);
    }
}
