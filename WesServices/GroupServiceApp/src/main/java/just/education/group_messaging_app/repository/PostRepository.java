package just.education.group_messaging_app.repository;

import just.education.group_messaging_app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
