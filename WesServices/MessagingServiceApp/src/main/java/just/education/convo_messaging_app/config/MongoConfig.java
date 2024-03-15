package just.education.convo_messaging_app.config;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoClientSettings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;


@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(final MongoProperties mongoProperties) {

        final MongoCredential credential = MongoCredential.createCredential(
                mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword()
        );

        final MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(
            final MongoClient mongoClient,
            final MongoProperties mongoProperties
    ) {

        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Bean
    public MongoTemplate mongoTemplate(final MongoDatabaseFactory mongoDatabaseFactory) {

        return new MongoTemplate(mongoDatabaseFactory);
    }
}