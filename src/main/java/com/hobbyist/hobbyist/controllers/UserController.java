package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
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

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "registration/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User newUser, @Validated User user, Errors validation, Model model) {
        String username = user.getUsername();
        String email = user.getEmail();
        User userExists = userDao.findByUsername(username);
        User emailExists = userDao.findByEmail(email);

        if (userExists != null) {
            validation.rejectValue("username", "user.username", username + " already exists. Please try again");
        }

        if (emailExists != null) {
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

    @GetMapping("/users/profile/{username}")
    public String showPublicUsersProfile(@PathVariable String username, Model vModel){
        User user = userDao.findByUsername(username);
        vModel.addAttribute("user", user);
        return "users/publicProfiles";
    }

    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model vModel) {

        vModel.addAttribute("user", userDao.findByUsername(username));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           vModel.addAttribute("userName", user.getUsername());
        //user must be logged in to view this page
//        User currentUser = userDao.findByUsername(username);
//        vModel.addAttribute("user", currentUser);
//        vModel.addAttribute("userName", currentUser.getUsername());
//        if(logUser == null){
//            vModel.addAttribute("msg", "Please log in");
//            return"error/custom";
//        }
        return "users/profile";
    }

    public Boolean checkEditAuth(User user) {
        return userService.isLoggedIn() && (user.getId() == userService.loggedInUser().getId());
    }

    @GetMapping("users/{id}/edit")
    public String showEditProfile(@PathVariable long id, Model vModel) {
        User user = userDao.getOne(id);
//        vModel.addAttribute("user", userDao.findById(id));
        vModel.addAttribute("user", user);
//        vModel.addAttribute("showEditControls", userService.canEditProfile(user));
        return "users/edit";
    }


    @PostMapping("users/{id}/edit")
    public String editProfile(@PathVariable long id,  @ModelAttribute User userToEdit) {

        userToEdit.setId(id);
        userToEdit.setPassword(passwordEncoder.encode(userToEdit.getPassword()));
        userDao.save(userToEdit);
        return "redirect:/profile/" + userToEdit.getUsername();
    }
}