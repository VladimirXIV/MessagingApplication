package just.education.security_messaging_app.dto;

import java.util.Objects;


public class UserLoginResponse {

    private String username;
    private String accessToken;
    private String refreshToken;


    public UserLoginResponse() {
    }

    public UserLoginResponse(String username, String accessToken, String refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginResponse response = (UserLoginResponse) o;
        return Objects.equals(username, response.username)
                && Objects.equals(accessToken, response.accessToken)
                && Objects.equals(refreshToken, response.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, accessToken, refreshToken);
    }
}