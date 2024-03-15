package just.education.security_messaging_app.security;

import java.util.Optional;

import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.service.UserPrincipal;
import just.education.security_messaging_app.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class DomainUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt = this.userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserPrincipal(userOpt.get());
    }
}