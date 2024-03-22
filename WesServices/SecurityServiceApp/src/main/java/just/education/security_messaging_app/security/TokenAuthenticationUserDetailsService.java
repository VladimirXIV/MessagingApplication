package just.education.security_messaging_app.security;

import just.education.security_messaging_app.model.AccessToken;
import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.repository.AccessTokenRepository;
import just.education.security_messaging_app.service.UserPrincipal;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import just.education.security_messaging_app.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Objects;
import java.util.Optional;


public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private AccessTokenRepository accessTokenRepository;


    public TokenAuthenticationUserDetailsService(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }


    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {

        ObjectId userId = (ObjectId) token.getPrincipal();

        Optional<AccessToken> accessTokenOpt = this.accessTokenRepository.findById(userId);
        if (accessTokenOpt.isEmpty()) {
            throw new UsernameNotFoundException("Access token not find");
        }

        User user = accessTokenOpt.get().getUser();
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User must be not NULL");
        }

        return new UserPrincipal(user);
    }
}
