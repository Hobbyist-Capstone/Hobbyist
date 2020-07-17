package com.hobbyist.hobbyist.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;

@Controller
public class IndexController {


    private HobbyRepository hobbyDao;

    public IndexController(HobbyRepository hobbyDao) {
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hobbies", hobbyDao.findAll());
        return "index/index";
    }
}

