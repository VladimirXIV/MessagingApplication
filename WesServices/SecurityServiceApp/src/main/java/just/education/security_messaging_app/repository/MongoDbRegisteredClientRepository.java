package just.education.security_messaging_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import just.education.security_messaging_app.model.MongoDbOAuth2RegisteredClient;

public interface MongoDbRegisteredClientRepository extends MongoRepository<MongoDbOAuth2RegisteredClient, String> {


}