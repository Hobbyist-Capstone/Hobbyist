package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Category;
import com.hobbyist.hobbyist.models.Hobby;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.RatingRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HobbyController {

    private HobbyRepository hobbyDao;
    private UserRepository userDao;
    private CategoryRepository categoryDao;
    private RatingRepository ratingDao;

    public HobbyController(HobbyRepository hobbyDao, CategoryRepository categoryDao, UserRepository userDao, RatingRepository ratingDao) {
        this.hobbyDao = hobbyDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
        this.ratingDao = ratingDao;
    }


    //     displays all hobbies
    @GetMapping("/hobbies")
    public String allHobbies(Model model) {
        model.addAttribute("hobbies", hobbyDao.findAll());
        return "hobbies/allHobbiesView";
    }

    //     displays a single hobby by {id}
    @GetMapping("/hobby/{id}")
    public String singleHobby(@PathVariable long id, Model model) {
        model.addAttribute("hobby", hobbyDao.getOne(id));
        return "hobby/hobbyView";
    }

    //    create a hobby
    @GetMapping("/hobby/create")
    public String createHobbyForm(Model model) {

        model.addAttribute("ratings");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("hobby", new Hobby());
        return "hobby/create";
    }

    //    post a created hobby
    @PostMapping("/hobby/create")
    public String saveCreatedHobby(@ModelAttribute Hobby saveHobby, @RequestParam(name = "categories") List<Long> categoriesId, @RequestParam(name = "patience") byte pat, @RequestParam(name = "difficulty") byte diff, @RequestParam(name = "cost") byte cost) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Category> categories = categoryDao.findAllById(categoriesId);
        saveHobby.setPatience(pat);
        saveHobby.setDifficulty(diff);
        saveHobby.setCost(cost);
        saveHobby.setCreatedBy(currentUser);
        saveHobby.setCategories(categories);
        hobbyDao.save(saveHobby);
        return "redirect:/hobby/" + saveHobby.getId();
    }

    //    show edited hobby
    @GetMapping("hobby/{id}/edit")
    public String showEdit(@PathVariable long id, Model model) {
        Hobby hobby = hobbyDao.getOne(id);
        model.addAttribute("hobby", hobby);
        return "hobby/hobbyEdit";
    }

    //    edit single hobby
    @PostMapping("hobby/{id}/edit")
    public String update(@ModelAttribute Hobby editHobby) {
        // save changes
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        editHobby.setCreatedBy(currentUser);
        hobbyDao.save(editHobby);
        return "redirect:/hobby/" + editHobby.getId();
    }

    @PostMapping("/hobby/{id}/delete")
    public String destroy(@ModelAttribute Hobby deleteHobby) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deleteHobby.setCreatedBy(currentUser);
        hobbyDao.delete(deleteHobby);
        return "redirect:/hobbies";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "search") String search) {
        List<Hobby> hobbies = hobbyDao.searchByTitle(search);
        model.addAttribute("hobbies", hobbies);
        return "hobbies/allHobbiesView";
    }

//    Hobby Comment Page TESTS

        @GetMapping("/comment-page")
    public String comments(Model model) {
        return "hobby/comment-page";
    }

    @GetMapping("/comment-page-2")
    public String comments2(Model model) {
        return "hobby/comment-page-2";
    }



}