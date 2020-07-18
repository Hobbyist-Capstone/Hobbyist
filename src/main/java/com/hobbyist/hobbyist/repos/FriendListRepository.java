package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendListRepository extends JpaRepository <FriendList, Long> {
//    List<FriendList> findAllByStatus(String status);

}
