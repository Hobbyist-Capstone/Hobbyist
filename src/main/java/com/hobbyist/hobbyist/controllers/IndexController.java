package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private HobbyRepository hobbyDao;

    public IndexController(HobbyRepository hobbyDao) {
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        String c1 = "fishing";
        String c2 = "hunting";
        String c3 = "reading";

        model.addAttribute("c1", hobbyDao.filterByCategory(c1));
        model.addAttribute("c2", hobbyDao.filterByCategory(c2));
        model.addAttribute("c3", hobbyDao.filterByCategory(c3));
        return "index";
    }

//    @GetMapping("/search")
//    public String search(Model model, @RequestParam(name = "search") String search) {
//
//    }
}

