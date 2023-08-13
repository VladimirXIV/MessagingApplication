package just.education.security_messaging_app.dto;

import java.util.Objects;


public class UserLoginResponse {

    private String username;
    private String token;


    public UserLoginResponse() {
    }

    public UserLoginResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginResponse response = (UserLoginResponse) o;
        return Objects.equals(username, response.username)
                && Objects.equals(token, response.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, token);
    }
}