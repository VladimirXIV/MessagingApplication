package just.education.security_messaging_app.model;

import java.util.Set;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import just.education.security_messaging_app.security.OAuth2TokenSettings;
import just.education.security_messaging_app.security.OAuth2ClientSettings;
import just.education.security_messaging_app.enums.OAuth2AuthorizationGrantType;
import just.education.security_messaging_app.enums.OAuth2ClientAuthenticationMethod;


@Document(collection = "registered_client")
public class MongoDbOAuth2RegisteredClient {

    private String id;
    private String clientId;
    private Date clientIdIssuedAt;
    private String clientSecret;
    private Date clientSecretExpiresAt;
    private String clientName;
    private Set<OAuth2ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<OAuth2AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private OAuth2ClientSettings clientSettings;
    private OAuth2TokenSettings tokenSettings;

    public MongoDbOAuth2RegisteredClient(
            String id,
            String clientId,
            Date clientIdIssuedAt,
            String clientSecret,
            Date clientSecretExpiresAt,
            String clientName,
            Set<OAuth2ClientAuthenticationMethod> clientAuthenticationMethods,
            Set<OAuth2AuthorizationGrantType> authorizationGrantTypes,
            Set<String> redirectUris, Set<String> scopes,
            OAuth2ClientSettings clientSettings,
            OAuth2TokenSettings tokenSettings
    ) {
        this.id = id;
        this.clientId = clientId;
        this.clientIdIssuedAt = clientIdIssuedAt;
        this.clientSecret = clientSecret;
        this.clientSecretExpiresAt = clientSecretExpiresAt;
        this.clientName = clientName;
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        this.authorizationGrantTypes = authorizationGrantTypes;
        this.redirectUris = redirectUris;
        this.scopes = scopes;
        this.clientSettings = clientSettings;
        this.tokenSettings = tokenSettings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(Date clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Date getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(Date clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<OAuth2ClientAuthenticationMethod> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(Set<OAuth2ClientAuthenticationMethod> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public Set<OAuth2AuthorizationGrantType> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(Set<OAuth2AuthorizationGrantType> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public OAuth2ClientSettings getClientSettings() {
        return clientSettings;
    }

    public void setClientSettings(OAuth2ClientSettings clientSettings) {
        this.clientSettings = clientSettings;
    }

    public OAuth2TokenSettings getTokenSettings() {
        return tokenSettings;
    }

    public void setTokenSettings(OAuth2TokenSettings tokenSettings) {
        this.tokenSettings = tokenSettings;
    }
}
