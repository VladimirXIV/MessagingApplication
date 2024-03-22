package just.education.security_messaging_app.security;

import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.http.HttpServletRequest;

import just.education.security_messaging_app.util.JwtHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import java.text.ParseException;


public class JwtAuthConverter implements AuthenticationConverter {

    //private static final String ROLES_CLAIM = "permissions";

    private JwtHelper jwtHelper;

    public JwtAuthConverter(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }


    /*@Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        Collection<GrantedAuthority> roles = this.extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, roles);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {

        Map<String, Object> realmAccess = jwt.getClaim("REALM_ACCESS");

        if (realmAccess != null) {

            final List<String> roles = (List<String>) realmAccess.get(ROLES_CLAIM);

            if (CollectionUtils.isNotEmpty(roles)) {

                return roles.
                        stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_PREFIX" + role))
                        .collect(Collectors.toSet());
                }
            }

        return SetUtils.emptySet();
    }*/

    @Override
    public Authentication convert(HttpServletRequest request) {

        final String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {

            String token = bearerToken.replace("Bearer ", "");

            if (jwtHelper.validateAccessToken(token)) {

                try {
                    JWTClaimsSet set = jwtHelper.getJwtClaims(token);

                    return new PreAuthenticatedAuthenticationToken(set, token);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
