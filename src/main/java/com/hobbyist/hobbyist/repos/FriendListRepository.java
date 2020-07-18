package com.hobbyist.hobbyist.repos;

import com.hobbyist.hobbyist.models.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.REQUIRED)
public interface FriendListRepository extends JpaRepository <FriendList, Long> {
//    List<FriendList> findAllByStatus(String status);

}
