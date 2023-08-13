package just.education.security_messaging_app.config;

import just.education.security_messaging_app.model.Role;
import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.repository.RoleRepository;
import just.education.security_messaging_app.repository.UserRepository;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;

@Configuration
public class MongoConfig {

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoProperties mongoProperties) {

        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {

        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Bean
    public MongoRepositoryFactoryBean<UserRepository, User, Long> userRepository(MongoTemplate mongoTemplate) {

        MongoRepositoryFactoryBean<UserRepository, User, Long> factoryBean = new MongoRepositoryFactoryBean<>(UserRepository.class);
        factoryBean.setMongoOperations(mongoTemplate);

        return factoryBean;
    }

    @Bean
    public MongoRepositoryFactoryBean<RoleRepository, Role, Long> roleRepository(MongoTemplate mongoTemplate) {

        MongoRepositoryFactoryBean<RoleRepository, Role, Long> factoryBean = new MongoRepositoryFactoryBean<>(RoleRepository.class);
        factoryBean.setMongoOperations(mongoTemplate);

        return factoryBean;
    }
}
