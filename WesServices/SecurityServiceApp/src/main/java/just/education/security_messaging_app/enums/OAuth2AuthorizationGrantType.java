package just.education.security_messaging_app.enums;

public enum OAuth2AuthorizationGrantType {

    /* Authorization code OAuth2 authorization grant type */
    AUTHORIZATION_CODE("authorization_code"),
    /* Refresh token OAuth2 authorization grant type */
    REFRESH_TOKEN("refresh_token"),
    /* Client credentials OAuth2 authorization grant type */
    CLIENT_CREDENTIALS("client_credentials"),
    /* Password OAuth2 authorization grant type */
    PASSWORD("password"),
    /* Jwt bearer OAuth2 authorization grant type */
    JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer");

    private final String value;

    OAuth2AuthorizationGrantType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}