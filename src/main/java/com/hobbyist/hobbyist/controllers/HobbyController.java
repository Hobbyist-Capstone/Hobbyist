package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HobbyController {

    private HobbyRepository hobbyDao;
    private UserRepository userDao;

    public HobbyController () {}

    public HobbyController(UserRepository userDao) {
        this.userDao = userDao;
    }

    public HobbyController(HobbyRepository hobbyDao) {
        this.hobbyDao = hobbyDao;
    }

    // displays a single hobby
    @GetMapping("/hobby")
    public String singleHobby(Model model) {
        model.addAttribute("hobby", hobbyDao.getOne(2L));
        model.addAttribute("status", userDao.getOne(2L));
        return "hobby/hobbyView";
    }

    // displays all hobbies
    @GetMapping("/hobbies")
    public String index(Model model) {
        model.addAttribute("hobbies", hobbyDao.findAll());
        model.addAttribute("status", userDao.findAll());
        return "hobbies/allHobbiesView";
    }
}