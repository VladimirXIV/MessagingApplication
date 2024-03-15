package just.education.user_messaging_app.converter;

import java.time.Instant;
import java.util.Collection;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import static org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER;


public class UserInfoJwtAuthenticationConverter implements Converter<Jwt, BearerTokenAuthentication> {

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService;


    public UserInfoJwtAuthenticationConverter() {

        this.oauth2UserService = new DefaultOAuth2UserService();
    }


    @Override
    public <U> Converter<Jwt, U> andThen(Converter<? super BearerTokenAuthentication, ? extends U> after) {

        return Converter.super.andThen(after);
    }

    @Override
    public BearerTokenAuthentication convert(Jwt jwt) {

        OAuth2AuthenticatedPrincipal principal = this.invokeUserInfo(jwt);

        Instant issuedAt = jwt.getIssuedAt();
        Instant expiresAt = jwt.getExpiresAt();
        OAuth2AccessToken token = new OAuth2AccessToken(BEARER, jwt.getTokenValue(), issuedAt, expiresAt);

        Collection<GrantedAuthority> authorities = (new JwtGrantedAuthoritiesConverter()).convert(jwt);

        return new BearerTokenAuthentication(principal, token, authorities);
    }

    private OAuth2AuthenticatedPrincipal invokeUserInfo(Jwt jwt) {

        ClientRegistration registration = ClientRegistration.withRegistrationId("Nibiru").userInfoUri("").build();

        OAuth2AccessToken token = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, jwt.getTokenValue(), null, null);
        OAuth2UserRequest oauth2UserRequest = new OAuth2UserRequest(registration, token);

        return this.oauth2UserService.loadUser(oauth2UserRequest);
    }
}