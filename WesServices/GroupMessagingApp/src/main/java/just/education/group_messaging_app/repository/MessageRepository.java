package just.education.group_messaging_app.repository;

import just.education.group_messaging_app.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {



}
