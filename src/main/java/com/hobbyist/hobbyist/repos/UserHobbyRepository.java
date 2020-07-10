package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.UserHobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHobbyRepository extends JpaRepository<UserHobby, Long> {
}
