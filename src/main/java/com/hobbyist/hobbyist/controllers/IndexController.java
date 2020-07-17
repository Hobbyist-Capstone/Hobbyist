package com.hobbyist.hobbyist.controllers;


import com.hobbyist.hobbyist.repos.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private CategoryRepository categoryDao;
    private HobbyRepository hobbyDao;


    public IndexController(HobbyRepository hobbyDao, CategoryRepository categoryDao) {
        this.hobbyDao = hobbyDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("c1", hobbyDao.filterByCategory(1L));
        model.addAttribute("c2", hobbyDao.filterByCategory(2L));
        model.addAttribute("c3", hobbyDao.filterByCategory(3L));
//        model.addAttribute("c4", hobbyDao.filterByCategory(4L));
        return "index/index";
    }
}

