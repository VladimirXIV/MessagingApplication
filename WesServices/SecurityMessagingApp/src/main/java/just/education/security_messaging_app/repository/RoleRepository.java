package just.education.security_messaging_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import just.education.security_messaging_app.model.Role;


public interface RoleRepository extends MongoRepository<Role, Long> {


}