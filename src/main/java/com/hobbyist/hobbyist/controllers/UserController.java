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

    //public profile
    @GetMapping("/users/profile/{username}")
    public String showPublicUsersProfile(@PathVariable String username, Model vModel) {
        User user = userDao.findByUsername(username);
        vModel.addAttribute("user", user);
        return "users/publicProfiles";
    }

    //user logged in profile
    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model vModel) {

        vModel.addAttribute("user", userDao.findByUsername(username));
//        User user = userDao.findByUsername(username);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if (user.isAdmin()) {
        vModel.addAttribute("userName", currentUser.getUsername());
//        }

        return "users/profile";
    }

    @GetMapping("profile/{id}/status")
    public String showHobbyStatusPage(@PathVariable long id, Model model) {

        //user that is the current session
        //spring security session
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //how to access the current user
        User userInDb = userDao.getOne(currentUser.getId());

        //list of hobbies
        List<Hobby> listOfHobbies = userInDb.getHobbies();

        //get first hobby in the users list
        String firstHobby = listOfHobbies.get(0).getTitle();


        System.out.println("first " + firstHobby);
        System.out.println("first ");



        for(Hobby hobby : listOfHobbies) {
//            System.out.println(userHobbyDao.findByUserIdAndHobbyId(currentUser.getId(),hobby.getId()).getStatus());
//            System.out.println(userHobbyDao.findByHobbyId(hobby.getId()).getStatus());
            model.addAttribute("hobbyStatus", userHobbyDao.findByUserIdAndHobbyId(currentUser.getId(),hobby.getId()).getStatus());
        }
//        String hobby = hobbyDao.getOne(0L).getTitle();
//        System.out.println(hobby);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userInDb", userInDb);
        model.addAttribute("listOfHobbies", listOfHobbies);
        model.addAttribute("firstHobby", firstHobby);


        return "users/hobbyStatus";

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
    public String editProfile(@PathVariable long id, @ModelAttribute User userToEdit) {

        userToEdit.setId(id);
        userToEdit.setPassword(passwordEncoder.encode(userToEdit.getPassword()));
        userDao.save(userToEdit);
        return "redirect:/profile/" + userToEdit.getUsername();
    }


}