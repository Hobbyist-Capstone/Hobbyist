package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.FriendList;
import com.hobbyist.hobbyist.models.FriendStatus;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.FriendListRepository;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class FriendController {

    private UserRepository userDao;
    private FriendListRepository friendListDao;

    public FriendController(UserRepository userDao,FriendListRepository  friendListDao) {
        this.userDao = userDao;
        this.friendListDao = friendListDao;

    }

    @PostMapping("/users/{username}/friend-request")
    public String sendFriendRequest(@PathVariable  String username, @ModelAttribute long userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FriendList friend = friendListDao.save(new FriendList(currentUser, userDao.getOne(userId), FriendStatus.PENDING));
        return "redirect:/profile/";
    }

}
