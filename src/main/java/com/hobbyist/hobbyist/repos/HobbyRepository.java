package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.Hobby;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
//    Hobby findByHobbyId(long id);
//    Hobby findByUserId(long id);
//    List <Hobby> findAllByStatus (String status);



    // This is will be used for the search functionality
    @Query("from Hobby a where a.title like %:term%")
    List<Hobby> searchByTitle(@Param("term") String term);


    // this will filter hobbies by category
//    select *
//    from hobbies
//    join hobby_categories hc on hobbies.id = hc.hobby_id
    @Query("from Hobby h inner join h.categories c where c.id = ?1")
    List<Hobby> filterByCategory(List<Long> id);

    @Query("from Hobby h inner join h.categories c where c.id = ?1")
    List<Hobby> filterByOneCategory(long id);
}
