package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
