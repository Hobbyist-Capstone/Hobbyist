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
        String cat1 = "Fishing";
        String cat2 = "Hunting";
        String cat3 = "Witchcraft";
        String cat4 = "Palm Reading";
        String cat5 = "Arguing";
        String cat6 = "Trolling";
        model.addAttribute("cat1", hobbyDao.filterByCategory(cat1));
        model.addAttribute("cat2", hobbyDao.filterByCategory(cat2));
        model.addAttribute("cat3", hobbyDao.filterByCategory(cat3));
        model.addAttribute("cat4", hobbyDao.filterByCategory(cat4));
        model.addAttribute("cat5", hobbyDao.filterByCategory(cat5));
        model.addAttribute("cat6", hobbyDao.filterByCategory(cat6));
        return "index";
    }

//    @GetMapping("/search")
//    public String search(Model model, @RequestParam(name = "search") String search) {
//
//    }
}

