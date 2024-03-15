package just.education.security_messaging_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;

import just.education.security_messaging_app.util.JwtHelper;
import just.education.security_messaging_app.mapper.UserMapper;
import just.education.security_messaging_app.service.UserService;
import just.education.security_messaging_app.repository.UserRepository;
import just.education.security_messaging_app.service.RefreshTokenService;
import just.education.security_messaging_app.repository.RefreshTokenRepository;


@Configuration
public class AppConfig {

    @Bean
    public UserMapper userMapper() {

        return UserMapper.INSTANCE;
    }

    @Bean
    public UserService userService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            JwtHelper jwtHelper
    ) {

        return new UserService(authenticationManager, passwordEncoder, userRepository, jwtHelper, userMapper());
    }

    @Bean
    public RefreshTokenService refreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            JwtHelper jwtHelper
    ) {

        return new RefreshTokenService(refreshTokenRepository, userRepository, jwtHelper);
    }
}