package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.HobbyStatus;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserHobbyRepository extends JpaRepository<UserHobby, Long> {
//    UserHobby findByUserId(long id);
    UserHobby findByHobbyId(long id);
    UserHobby findByUserIdAndHobbyId(long user_id, long hobby_id);
    List<UserHobby> findAllByUserHobbyStatus (String status);
//    List<UserHobby> findAllByHobbyStatus(String status);



}
