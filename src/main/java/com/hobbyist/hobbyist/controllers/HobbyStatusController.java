package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.HobbyStatus;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HobbyStatusController {

    private UserHobbyRepository userHobbyDao;
    private UserRepository userDao;

    public HobbyStatusController(UserHobbyRepository userHobbyDao, UserRepository userDao ){
        this.userHobbyDao = userHobbyDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile/{username}/status")
    @ResponseBody
    public String showHobbyStatusPage(@PathVariable String username){
        return "I am working" +username;
    }

}
