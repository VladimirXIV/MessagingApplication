package just.education.security_messaging_app.repository;

import just.education.security_messaging_app.enums.MongoDbSignatureAlgorithm;
import just.education.security_messaging_app.enums.OAuth2AuthorizationGrantType;
import just.education.security_messaging_app.enums.OAuth2ClientAuthenticationMethod;
import just.education.security_messaging_app.model.MongoDbOAuth2RegisteredClient;
import just.education.security_messaging_app.security.OAuth2ClientSettings;
import just.education.security_messaging_app.security.OAuth2TokenSettings;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.Date;
import java.util.stream.Collectors;

public class MongoRegisteredClientRepository implements RegisteredClientRepository {

    MongoDbRegisteredClientRepository mongoDbRegisteredClientRepository;


    public MongoRegisteredClientRepository(MongoDbRegisteredClientRepository mongoDbRegisteredClientRepository) {
        this.mongoDbRegisteredClientRepository = mongoDbRegisteredClientRepository;
    }


    @Override
    public void save(RegisteredClient registeredClient) {

        MongoDbOAuth2RegisteredClient client = new MongoDbOAuth2RegisteredClient(

                registeredClient.getId(),
                registeredClient.getClientId(),
                Date.from(registeredClient.getClientIdIssuedAt()),
                registeredClient.getClientSecret(),
                Date.from(registeredClient.getClientSecretExpiresAt()),
                registeredClient.getClientName(),
                registeredClient.getClientAuthenticationMethods().stream()
                        .map(this::resolveOauth2ClientAuthenticationMethod)
                        .collect(Collectors.toSet()),
                registeredClient.getAuthorizationGrantTypes().stream()
                        .map(this::resolveOauth2AuthorizationGrantType)
                        .collect(Collectors.toSet()),
                registeredClient.getRedirectUris(),
                registeredClient.getScopes(),
                new OAuth2ClientSettings(
                        registeredClient.getClientSettings().isRequireProofKey(),
                        registeredClient.getClientSettings().isRequireAuthorizationConsent()),
                new OAuth2TokenSettings(
                        registeredClient.getTokenSettings().getAccessTokenTimeToLive(),
                        registeredClient.getTokenSettings().isReuseRefreshTokens(),
                        registeredClient.getTokenSettings().getRefreshTokenTimeToLive(),
                        MongoDbSignatureAlgorithm.from(registeredClient.getTokenSettings().getIdTokenSignatureAlgorithm().getName())
                )
        );

        this.mongoDbRegisteredClientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }


    private OAuth2ClientAuthenticationMethod resolveOauth2ClientAuthenticationMethod(ClientAuthenticationMethod clientAuthenticationMethod) {
        if (OAuth2ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(clientAuthenticationMethod.getValue())) {
            return OAuth2ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
        } else if (OAuth2ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(clientAuthenticationMethod.getValue())) {
            return OAuth2ClientAuthenticationMethod.CLIENT_SECRET_POST;
        } else if (OAuth2ClientAuthenticationMethod.NONE.getValue().equals(clientAuthenticationMethod.getValue())) {
            return OAuth2ClientAuthenticationMethod.NONE;
        }
        return OAuth2ClientAuthenticationMethod.NONE;        // Custom client authentication method
    }

    private OAuth2AuthorizationGrantType resolveOauth2AuthorizationGrantType(AuthorizationGrantType authorizationGrantType) {
        if (OAuth2AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType.getValue())) {
            return OAuth2AuthorizationGrantType.AUTHORIZATION_CODE;
        } else if (OAuth2AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType.getValue())) {
            return OAuth2AuthorizationGrantType.CLIENT_CREDENTIALS;
        } else if (OAuth2AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType.getValue())) {
            return OAuth2AuthorizationGrantType.REFRESH_TOKEN;
        }
        return OAuth2AuthorizationGrantType.PASSWORD;        // FYI this is different than the example so might be an issue
    }
}
