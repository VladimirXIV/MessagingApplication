package just.education.security_messaging_app.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "refresh_token")
public class RefreshToken {

    @Id
    private ObjectId id;

    public String token;

    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @DBRef
    public User user;


    public enum TokenType {
        BEARER,
    }


    public RefreshToken() {
    }

    public RefreshToken(String token) {
        this.token = token;
    }

    public RefreshToken(ObjectId id, String token, TokenType tokenType, boolean revoked, boolean expired) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshToken token = (RefreshToken) o;
        return Objects.equals(this.revoked,token.revoked)
                && Objects.equals(this.expired,token.expired)
                && Objects.equals(this.id, token.id)
                && Objects.equals(this.token, token.token)
                && Objects.equals(this.tokenType, token.tokenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, tokenType, revoked, expired);
    }
}