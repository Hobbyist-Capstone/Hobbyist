package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String Email);

}
