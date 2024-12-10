package lk.talentfort.health_information_system.repository;

import lk.talentfort.health_information_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {


    User findUserByEmail(String email);

    @Query("SELECT MAX(u.patientId) FROM User u WHERE u.roles = 'ROLE_PATIENT'")
    String findLatestPatientId();

}
