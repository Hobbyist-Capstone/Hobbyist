package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Category;
import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;

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
        model.addAttribute("user", new User());
        model.addAttribute("c1", hobbyDao.filterByCategory(1));
        model.addAttribute("c2", hobbyDao.filterByCategory(2));
        model.addAttribute("c3", hobbyDao.filterByCategory(3));
        return "index/index";
    }

//    future search feature
//    @GetMapping("/search")
//    public String search(Model model, @RequestParam(name = "term") String term) {
//        List<Hobby> hobbies = hobbyDao.searchByTitle(term);
//        model.addAttribute("c", hobbies);
//        return "index";
//    }

}

