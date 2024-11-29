package lk.talentfort.health_information_system.repository;

import lk.talentfort.health_information_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
