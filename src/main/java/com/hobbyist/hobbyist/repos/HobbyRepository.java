package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
