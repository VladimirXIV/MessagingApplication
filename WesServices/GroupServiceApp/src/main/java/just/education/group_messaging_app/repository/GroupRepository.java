package just.education.group_messaging_app.repository;

import just.education.group_messaging_app.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
}