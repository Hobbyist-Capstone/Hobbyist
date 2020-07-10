package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
