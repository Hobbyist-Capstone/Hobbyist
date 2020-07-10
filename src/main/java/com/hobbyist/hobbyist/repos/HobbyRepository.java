package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
