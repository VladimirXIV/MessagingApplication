package just.education.security_messaging_app.config;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoClientSettings;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;

import just.education.security_messaging_app.model.Role;
import just.education.security_messaging_app.model.User;
import just.education.security_messaging_app.model.RefreshToken;
import just.education.security_messaging_app.repository.RoleRepository;
import just.education.security_messaging_app.repository.UserRepository;
import just.education.security_messaging_app.repository.RefreshTokenRepository;
import just.education.security_messaging_app.model.MongoDbOAuth2RegisteredClient;
import just.education.security_messaging_app.repository.MongoDbRegisteredClientRepository;


@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(MongoProperties mongoProperties) {

        MongoCredential credential = MongoCredential
                .createCredential(mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword());

        return MongoClients.create(
                MongoClientSettings.
                        builder()
                        //.applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                        .uuidRepresentation(mongoProperties.getUuidRepresentation())
                        .credential(credential)
                        .build());
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient, MongoProperties mongoProperties) {

        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
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

    @Bean
    public MongoRepositoryFactoryBean<RefreshTokenRepository, RefreshToken, Long> refreshTokenRepository(MongoTemplate mongoTemplate) {

        MongoRepositoryFactoryBean<RefreshTokenRepository, RefreshToken, Long> factoryBean = new MongoRepositoryFactoryBean<>(RefreshTokenRepository.class);
        factoryBean.setMongoOperations(mongoTemplate);

        return factoryBean;
    }

    @Bean
    public MongoRepositoryFactoryBean<MongoDbRegisteredClientRepository, MongoDbOAuth2RegisteredClient, String> mongoDbRegisteredClientRepository (MongoTemplate mongoTemplate) {

        MongoRepositoryFactoryBean<MongoDbRegisteredClientRepository, MongoDbOAuth2RegisteredClient, String> factoryBean = new MongoRepositoryFactoryBean<>(MongoDbRegisteredClientRepository.class);
        factoryBean.setMongoOperations(mongoTemplate);

        return factoryBean;
    }
}
