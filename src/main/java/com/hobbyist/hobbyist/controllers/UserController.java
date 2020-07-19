package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.*;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import com.hobbyist.hobbyist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private UserHobbyRepository userHobbyDao;
    private HobbyRepository hobbyDao;


    private UserService userService;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, UserHobbyRepository userHobbyDao, HobbyRepository hobbyDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userHobbyDao = userHobbyDao;
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return  "registration/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User newUser, @Validated User user, Errors validation, Model model) {
        String username = user.getUsername();
        String email = user.getEmail();
        User userExists = userDao.findByUsername(username);
        User emailExists = userDao.findByEmail(email);

        if (userExists != null) {
            validation.rejectValue("username", "user.username", username + " already exists in our records.");
        }

        if (emailExists != null) {
            validation.rejectValue("email", "user.email", email + " already exists in our records.");
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return  "registration/register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(newUser);
        return "redirect:/";
    }

//    //public profile
//    @GetMapping("/users/profile/{username}")
//    public String showPublicUsersProfile(@PathVariable String username, Model vModel) {
//        User user = userDao.findByUsername(username);
//        vModel.addAttribute("user", user);
//        return "users/publicProfiles";
//    }

}