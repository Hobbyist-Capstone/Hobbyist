package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.FriendList;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.FriendListRepository;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class FriendController {

    private FriendListRepository friendListDao;
    private UserHobbyRepository userHobbyDao;
    private UserRepository userDao;
    private HobbyRepository hobbyDao;



    public FriendController(FriendListRepository friendListDao, UserHobbyRepository userHobbyDao, UserRepository userDao, HobbyRepository hobbyDao) {
        this.friendListDao = friendListDao;
        this.userHobbyDao = userHobbyDao;
        this.userDao = userDao;
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/profile/friends")
    public String addFriends(@RequestParam long userId){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        User user = userDao.getOne(userId);

        return "friends/friends";
    }


}
