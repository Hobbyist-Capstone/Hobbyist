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
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Service
@Transactional
@Controller
public class FriendController {

    private UserRepository userDao;
    private FriendListRepository friendListDao;

    public FriendController(UserRepository userDao,FriendListRepository friendListDao) {
        this.userDao = userDao;
        this.friendListDao = friendListDao;
    }

    @GetMapping("/profile/friend-request")
    public String sendFriendRequest(@RequestParam long userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        User user = userDao.getOne(userId);
        FriendList friend = new FriendList(userInDb, user, FriendStatus.ACCEPTED);
        friendListDao.save(friend);
        return "redirect:/profile";
    }

}
