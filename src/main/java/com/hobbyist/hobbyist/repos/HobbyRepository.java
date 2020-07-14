package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    // This is will be used for the search functionality
    @Query("from Hobby a where a.title like %:term%")
    List<Hobby> searchByTitle(@Param("term") String term);

    // This is for the main page where hobbies are displayed by category string
    @Query("from Hobby a where a.category.id = ?1")
    List<Hobby> filterByCategory(@Param("category") long category);

}
