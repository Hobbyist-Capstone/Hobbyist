package com.hobbyist.hobbyist.controllers;


import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private CategoryRepository categoryDao;
    private HobbyRepository hobbyDao;
    private UserHobbyRepository userHobbyDao;
    private UserRepository  userDao;



    public IndexController(HobbyRepository hobbyDao, CategoryRepository categoryDao, UserHobbyRepository userHobbyDao, UserRepository  userDao
    ) {
        this.hobbyDao = hobbyDao;
        this.categoryDao = categoryDao;
        this.userHobbyDao = userHobbyDao;
        this.userDao = userDao;

    }

    @GetMapping("/")
    public String index(Model model, String username) {


//        User user = userDao.findByUsername(username);

//        model.addAttribute("user", user);
        model.addAttribute("hobbies", hobbyDao.findAll());
        return "index/index";
    }
}

