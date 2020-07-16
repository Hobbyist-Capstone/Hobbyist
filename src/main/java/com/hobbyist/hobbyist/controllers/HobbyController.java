package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Category;
import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HobbyController {

    private HobbyRepository hobbyDao;
    private UserRepository userDao;
    private CategoryRepository categoryDao;

    public HobbyController(HobbyRepository hobbyDao, CategoryRepository categoryDao, UserRepository userDao) {
        this.hobbyDao = hobbyDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
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
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("hobby", new Hobby());
        return "hobby/create";
    }

    //    post a created hobby
    @PostMapping("/hobby/create")
    public String saveCreatedHobby(@ModelAttribute Hobby saveHobby, @RequestParam(name="categories") List<Category> category_ids) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(category_ids);

        saveHobby.setCreatedBy(currentUser);
        saveHobby.setCategories(category_ids);
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
}