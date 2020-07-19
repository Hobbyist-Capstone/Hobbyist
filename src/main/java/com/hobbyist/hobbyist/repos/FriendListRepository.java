package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.FriendList;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface FriendListRepository extends JpaRepository <FriendList, Long> {
    List <FriendList> findAllByUserId(long id);


}