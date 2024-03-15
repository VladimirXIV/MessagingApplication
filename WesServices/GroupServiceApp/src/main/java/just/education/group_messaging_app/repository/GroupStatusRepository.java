package just.education.group_messaging_app.repository;

import just.education.group_messaging_app.entity.GroupStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupStatusRepository extends JpaRepository<GroupStatus, Long> {

    // get reference by code
    public GroupStatus getGroupStatusByCode(Long statusCode);
}