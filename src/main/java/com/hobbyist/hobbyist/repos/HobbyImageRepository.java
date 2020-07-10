package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.HobbyImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyImageRepository extends JpaRepository<HobbyImage, Long> {
}
