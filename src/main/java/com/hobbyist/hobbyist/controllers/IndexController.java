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

        model.addAttribute("hobbies", hobbyDao.findAll());
    public String index(Model model) {
        model.addAttribute("c1", hobbyDao.filterByCategory(1L));
        model.addAttribute("c2", hobbyDao.filterByCategory(2L));
        model.addAttribute("c3", hobbyDao.filterByCategory(3L));
        model.addAttribute("c4", hobbyDao.filterByCategory(4L));

        return "index/index";
    }
}

