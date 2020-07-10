package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.HobbyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyStatusRepository extends JpaRepository<HobbyStatus, Long> {
}
