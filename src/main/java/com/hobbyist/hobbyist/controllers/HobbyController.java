package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Category;
import com.hobbyist.hobbyist.models.Hobby;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.RatingRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HobbyController {

    @Value("${filestack.api.key}")
    private String apiKey;

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
        model.addAttribute("category", categoryDao.findAll());
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
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("hobby", new Hobby());
        model.addAttribute("apiKey", apiKey);
        return "hobby/create";
    }

    //    post a created hobby
    @PostMapping("/hobby/create")
    public String saveCreatedHobby(@ModelAttribute Hobby saveHobby, @RequestParam(name = "categories") List<Long> categoriesId, @RequestParam(name = "patience") byte pat, @RequestParam(name = "difficulty") byte diff, @RequestParam(name = "cost") byte cost, @RequestParam(name = "video") String video) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Category> categories = categoryDao.findByIdIn(categoriesId);
        if(!video.equals("")) {
            String youTubeVideo = new String(video);
            String youtubeString = youTubeVideo.substring(38, 79);
            saveHobby.setYoutubeLink(youtubeString);
        }
        saveHobby.setCreatedBy(currentUser);
        saveHobby.setCategories(categories);
        saveHobby.setPatience(pat);
        saveHobby.setDifficulty(diff);
        saveHobby.setCost(cost);
        hobbyDao.save(saveHobby);
        return "redirect:/hobby/" + saveHobby.getId();
    }

    //    show edited hobby
    @GetMapping("hobby/{id}/edit")
    public String showEdit(@PathVariable long id, Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        Hobby hobby = hobbyDao.getOne(id);
        model.addAttribute("hobby", hobby);
        model.addAttribute("apiKey", apiKey);
        return "hobby/hobbyEdit";
    }

    //    edit single hobby
    @PostMapping("hobby/{id}/edit")
    public String update(@ModelAttribute Hobby editHobby, @RequestParam(name = "categories", required = false) List<Long> categoriesId, @RequestParam(name = "patience") byte pat, @RequestParam(name = "difficulty") byte diff, @RequestParam(name = "cost") byte cost, @RequestParam(name = "video") String video, @RequestParam(name = "userId", required = false) Long userId) {
        // save changes
        List<Category> categories = categoryDao.findByIdIn(categoriesId);
        String youTubeVideo = new String(video);
        String youtubeString = youTubeVideo.substring(38, 79);
        editHobby.setYoutubeLink(youtubeString);
        editHobby.setCreatedBy(userDao.getOne(2L));
        editHobby.setCategories(categories);
        editHobby.setYoutubeLink(video);
        editHobby.setPatience(pat);
        editHobby.setDifficulty(diff);
        editHobby.setCost(cost);
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

    @PostMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "search") String search) {
        List<Hobby> hobbies = hobbyDao.searchByTitle(search);
        model.addAttribute("search", hobbyDao.findAll());
        model.addAttribute("hobbies", hobbies);
        return "hobbies/allHobbiesView";
    }

    @GetMapping("/category/{id}")
    public String filterByCategory(Model model, @PathVariable(name = "id", required = false) long categoryId) {
        model.addAttribute("hobbies", hobbyDao.filterByOneCategory(categoryId));
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