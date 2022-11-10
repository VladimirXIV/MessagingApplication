package just.education.messaging_app.config;

import just.education.messaging_app.repository.UserRepository;
import just.education.messaging_app.repository.PostRepository;
import just.education.messaging_app.service.UserService;
import just.education.messaging_app.service.PostService;
import just.education.messaging_app.serviceimpl.UserServiceImpl;
import just.education.messaging_app.serviceimpl.PostServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@ComponentScan
@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository(EntityManagerFactory entityManagerFactory) {
        return new UserRepository(entityManagerFactory);
    }

    @Bean
    public PostRepository userPostRepository(EntityManagerFactory entityManagerFactory) {
        return new PostRepository(entityManagerFactory);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public PostService userPostService(PostRepository userPostRepository) {
        return new PostServiceImpl(userPostRepository);
    }
}
