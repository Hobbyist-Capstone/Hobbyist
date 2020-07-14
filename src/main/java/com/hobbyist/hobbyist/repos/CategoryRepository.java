package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
