package just.education.security_messaging_app.dto;

import java.util.Objects;


public class UserRegisterResponse {

    private Long id;
    private String username;
    private String email;
    private String password;


    public UserRegisterResponse() {
    }

    public UserRegisterResponse(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisterResponse that = (UserRegisterResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(email, that.email)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }
}