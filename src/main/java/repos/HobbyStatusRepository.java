package repos;

import models.HobbyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyStatusRepository extends JpaRepository<HobbyStatus, Long> {
}
