package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private UserRepository usersDao;
    private HobbyRepository hobbyDao;

    public AdminController(UserRepository usersDao, HobbyRepository hobbyDao) {
        this.usersDao = usersDao;
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/admin")
    public String siteAdmin(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.isAdmin()) {
            model.addAttribute("users", usersDao.findAll());
            model.addAttribute("hobbies", hobbyDao.findAll());
            return "admin/admin";
        } else {
            return "registration/login";
        }
    }

    @PostMapping("/admin/delete")
    public String deleteProfile(@RequestParam long userId){
        User deleteProfile = usersDao.getOne(userId);
        usersDao.delete(deleteProfile);
        return "redirect:/admin";
    }
}
