package just.education.security_messaging_app.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;

import just.education.security_messaging_app.model.Role;
import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.mapper.UserMapper;
import just.education.security_messaging_app.util.JWTGenerator;
import just.education.security_messaging_app.service.UserService;
import just.education.security_messaging_app.repository.RoleRepository;
import just.education.security_messaging_app.repository.UserRepository;


@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {

        return new UserService();
    }

    @Bean
    public UserMapper userMapper() {

        return UserMapper.INSTANCE;
    }

    @Bean
    public UserService authenticationService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            JWTGenerator jwtGenerator) {

        return new UserService(authenticationManager, passwordEncoder, userRepository, roleRepository, jwtGenerator, userMapper());
    }
}