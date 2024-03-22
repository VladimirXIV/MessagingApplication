package just.education.security_messaging_app.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import just.education.security_messaging_app.model.AccessToken;


public interface AccessTokenRepository extends MongoRepository<AccessToken, Long> {

    Optional<AccessToken> findById(ObjectId id);

    Optional<AccessToken> findByUserUsername(String username);
    boolean existsById(ObjectId id);
    void deleteById(ObjectId id);
}