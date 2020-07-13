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
        String c1 = "sports";
        String c2 = "trolling";
        String c3 = "crafts";

        model.addAttribute("c1", hobbyDao.filterByCategory(c1));
        model.addAttribute("c2", hobbyDao.filterByCategory(c2));
        model.addAttribute("c3", hobbyDao.filterByCategory(c3));
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

