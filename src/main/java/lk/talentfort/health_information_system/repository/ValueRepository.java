package lk.talentfort.health_information_system.repository;

import lk.talentfort.health_information_system.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Value,Long> {
}
