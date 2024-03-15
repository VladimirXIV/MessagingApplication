package just.education.security_messaging_app.repository;

import just.education.security_messaging_app.model.RefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserUsername(String username);
    boolean existsById(ObjectId id);
    void deleteById(ObjectId id);
}