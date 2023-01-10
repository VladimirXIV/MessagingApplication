package just.education.group_messaging_app.repository;

import just.education.group_messaging_app.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
