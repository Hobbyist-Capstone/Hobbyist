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
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FriendListController {

    private UserRepository userDao;
    private FriendListRepository friendListDao;

    public FriendListController(UserRepository userDao,FriendListRepository friendListDao) {
        this.userDao = userDao;
        this.friendListDao = friendListDao;
    }

    @GetMapping("/users/profile/friends-request")
    public String sendFriendRequest( @RequestParam long userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        User user = userDao.getOne(userId);

        //friend userId
        FriendList friends = friendListDao.getOne(userId);
        System.out.println(friends.getId());

        FriendList friendExists = friendListDao.findByUserId(friends.getId());
        //if user == friends, do not make a new occurrance in db.

        FriendList friend = new FriendList(currentUser, user, FriendStatus.ACCEPTED);
        friendListDao.save(friend);
        return "redirect:/users/profile/" + user.getUsername();
    }

    @PostMapping("/profile/friends-request/delete")
    public String delete(@RequestParam long deleteFriendId){
        friendListDao.deleteById(deleteFriendId);
        return "redirect:/profile";
    }
}