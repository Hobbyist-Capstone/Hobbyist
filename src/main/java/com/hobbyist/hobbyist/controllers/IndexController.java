package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Category;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {


    private HobbyRepository hobbyDao;
    private CategoryRepository categoryDao;

    public IndexController(HobbyRepository hobbyDao, CategoryRepository categoryDao) {
        this.hobbyDao = hobbyDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        Category category1 = categoryDao.getOne(1L);
        Category category2 = categoryDao.getOne(2L);
        Category category3 = categoryDao.getOne(3L);
        System.out.println(category1.getName());

        String c1 = "sports";
        String c2 = "trolling";
        String c3 = "crafts";

        model.addAttribute("categoryName1", category1.getName());
        model.addAttribute("c1", hobbyDao.filterByCategory(c1));

        model.addAttribute("categoryName2", category2.getName());
        model.addAttribute("c2", hobbyDao.filterByCategory(c2));

        model.addAttribute("categoryName3", category3.getName());
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

