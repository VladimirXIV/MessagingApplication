package just.education.group_messaging_app.repository;

import just.education.group_messaging_app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
