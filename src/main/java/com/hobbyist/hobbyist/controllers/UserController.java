package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "registration/register";
    }

//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User newUser, @Validated User user, Errors validation, Model model) {
        String username = user.getUsername();
        String email = user.getEmail();
        User userExists = userDao.findByUsername(username);
        User emailExists = userDao.findByEmail(email);

        if (userExists != null) {
            validation.rejectValue("username", "user.username", username + " already exists. Please try again");
        }

        if (emailExists !=null){
            validation.rejectValue("email", "user.email", email + " already exists in our records. Please sign-in with the corresponding username");
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "registration/register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(newUser);
        return "redirect:/login";
    }

//    @GetMapping("/profile")
//    public String showProfile( Model vModel){
////        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        vModel.addAttribute("user",currentUser);
//
////        if(logUser == null){
////            vModel.addAttribute("msg", "Please log in");
////            return"error/custom";
////        }
//
//        return "profile/profile" + currentUser.getId();
//    }
}