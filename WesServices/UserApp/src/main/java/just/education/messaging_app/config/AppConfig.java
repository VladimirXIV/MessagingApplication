package just.education.messaging_app.config;

import just.education.messaging_app.repository.UserRepository;
import just.education.messaging_app.repository.PostRepository;
import just.education.messaging_app.repository.MessageRepository;
import just.education.messaging_app.service.MessageService;
import just.education.messaging_app.service.UserService;
import just.education.messaging_app.service.PostService;
import just.education.messaging_app.serviceimpl.MessageServiceImpl;
import just.education.messaging_app.serviceimpl.UserServiceImpl;
import just.education.messaging_app.serviceimpl.PostServiceImpl;
import just.education.messaging_app.mapper.UserMapper;
import just.education.messaging_app.mapper.PostMapper;
import just.education.messaging_app.mapper.MessageMapper;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@ComponentScan
@Configuration
public class AppConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public PostMapper postMapper() {
        return new PostMapper();
    }

    @Bean
    public MessageMapper messageMapper() {
        return new MessageMapper();
    }

    @Bean
    public UserRepository userRepository(EntityManagerFactory entityManagerFactory) {
        return new UserRepository(entityManagerFactory);
    }

    @Bean
    public PostRepository userPostRepository(EntityManagerFactory entityManagerFactory) {
        return new PostRepository(entityManagerFactory);
    }

    @Bean
    public MessageRepository messageRepository(EntityManagerFactory entityManagerFactory) {
        return new MessageRepository(entityManagerFactory);
    }

    @Bean
    public UserService userService(UserRepository userRepository, UserMapper userMapper) {
        return new UserServiceImpl(userRepository, userMapper);
    }

    @Bean
    public PostService userPostService(PostRepository userPostRepository, PostMapper postMapper) {
        return new PostServiceImpl(userPostRepository, postMapper);
    }

    @Bean
    public MessageService messageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        return new MessageServiceImpl(messageRepository, messageMapper);
    }
}
