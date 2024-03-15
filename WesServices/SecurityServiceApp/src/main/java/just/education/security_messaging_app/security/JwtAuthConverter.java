package just.education.security_messaging_app.security;

import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.CollectionUtils;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;


public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String ROLES_CLAIM = "permissions";

    @Override
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
    }
}
