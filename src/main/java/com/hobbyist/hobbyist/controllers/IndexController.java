package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Category;
import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {


    private HobbyRepository hobbyDao;
    private CategoryRepository categoryDao;
    private UserHobbyRepository userHobbyDao;
    private UserRepository userDao;

    public IndexController(HobbyRepository hobbyDao) {
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hobbies", hobbyDao.findAll());
        return "index/index";
    }
}

