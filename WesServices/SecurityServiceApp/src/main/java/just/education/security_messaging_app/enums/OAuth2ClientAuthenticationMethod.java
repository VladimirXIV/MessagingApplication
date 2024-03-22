package just.education.security_messaging_app.enums;

public enum OAuth2ClientAuthenticationMethod {

    /* Client secret basic OAuth2 client authentication method */
    CLIENT_SECRET_BASIC("client_secret_basic"),
    /* Client secret post OAuth2 client authentication method */
    CLIENT_SECRET_POST("client_secret_post"),
    /* Client secret jwt OAuth2 client authentication method */
    CLIENT_SECRET_JWT("client_secret_jwt"),
    /* Private key jwt OAuth2 client authentication method */
    PRIVATE_KEY_JWT("private_key_jwt"),
    /* None OAuth2 client authentication method */
    NONE("none");

    private final String value;

    OAuth2ClientAuthenticationMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}