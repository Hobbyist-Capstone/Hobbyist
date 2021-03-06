package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.FriendList;
import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.*;
import com.hobbyist.hobbyist.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfileController {

    @Value("${filestack.api.key}")
    private String apiKey;

//    @GetMapping("/profile-view")
//    public String profile(Model model) {
//        return "users/profile-view";
//    }

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private UserHobbyRepository userHobbyDao;
    private HobbyRepository hobbyDao;
    private FriendListRepository friendListDao;


    private UserService userService;

    public ProfileController(UserRepository userDao, PasswordEncoder passwordEncoder, UserHobbyRepository userHobbyDao, HobbyRepository hobbyDao, FriendListRepository friendListDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userHobbyDao = userHobbyDao;
        this.hobbyDao = hobbyDao;
        this.friendListDao = friendListDao;
    }


    //user logged in profile
    @GetMapping("/profile")
    public String showProfile(Model vModel) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        vModel.addAttribute("user", userDao.findByUsername(currentUser.getUsername()));

        List<UserHobby> userHobby = userHobbyDao.findAllByUserId(currentUser.getId());
        vModel.addAttribute("userHobbyList", userHobby);
        vModel.addAttribute("friendsList", userInDb.getFriends());

//        vModel.addAttribute("userNameHobby", currentUser.getUsername());
        vModel.addAttribute("currentUsername", currentUser.getUsername());

        List<Hobby> hobby = hobbyDao.findAll();
        vModel.addAttribute("hobbies", hobby);

        return "users/profile-view";
    }

    //public profile - this is the most accurate friendslist for the user that is not "you"
    @GetMapping("/users/profile/{username}")
    public String showPublicUsersProfile(@PathVariable String username, Model vModel) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        User user = userDao.findByUsername(username);
        List<UserHobby> userHobby = userHobbyDao.findAllByUserId(user.getId());
        vModel.addAttribute("user", user);
        vModel.addAttribute("userHobbyList", userHobby);
        vModel.addAttribute("friendsList", user.getFriends());
        vModel.addAttribute("user", userDao.findByUsername(username));
        vModel.addAttribute("publicUsername", user.getUsername());
        List<Hobby> hobby = hobbyDao.findAll();
        vModel.addAttribute("hobbies", hobby);


        vModel.addAttribute("publicUserId", user.getId());
        vModel.addAttribute("currentUserId", currentUser.getId());

        return "users/profile-view";
    }

//
    @GetMapping("/profile/{username}")
    public String showUsersProfile(Model vModel, @PathVariable String username) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        vModel.addAttribute("user", userInDb);
        vModel.addAttribute("friendsList", userInDb.getFriends());
        vModel.addAttribute("user", userDao.findByUsername(username));
        vModel.addAttribute("userName", userInDb.getUsername());
        return "users/profile-view";
    }

    @GetMapping("users/{id}/edit")
    public String showEditProfile(@PathVariable long id, Model vModel) {
        User user = userDao.getOne(id);
        vModel.addAttribute("user", user);
        vModel.addAttribute("apiKey", apiKey);
        return "users/edit";
    }


    @PostMapping("users/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User userToEdit, Model model) {
//        public String editProfile(@PathVariable long id, @ModelAttribute User userToEdit, @Validated User user, Errors validation, Model model) {
//        String username = user.getUsername();
//        String email = user.getEmail();
//        User userExists = userDao.findByUsername(username);
//        User emailExists = userDao.findByEmail(email);

//        if (userExists != null) {
//            validation.rejectValue("username", "user.username", username + " already exists in our records.");
//        }
//
//        if (emailExists != null) {
//            validation.rejectValue("email", "user.email", email + " already exists in our records.");
//        }
//
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("user", user);
//            return  "registration/register";
//        }
        model.addAttribute("apiKey", apiKey);
        userToEdit.setId(id);
        userToEdit.setPassword(passwordEncoder.encode(userToEdit.getPassword()));
        userDao.save(userToEdit);
        return "redirect:/profile";
    }

//    @GetMapping("users/{id}/upload")
//    public String showUploadImage(@PathVariable long id, Model vModel) {
//        User user = userDao.getOne(id);
//            model.addAttribute("apiKey", apiKey);
//        vModel.addAttribute("user", user);
//        return "users/uploadImage";
//    }
//
//
//    @PostMapping("users/upload")
//    public String editUploadImage(@ModelAttribute User userToEdit) {
//        userDao.save(userToEdit);
//        return "redirect:/profile";
//    }

//    @GetMapping("users/hobby")
//    public String showCreatedHobbies(Model model){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userInDb = userDao.getOne(currentUser.getId());
//        model.addAttribute("userNameHobby", currentUser.getUsername());
//        model.addAttribute("user", currentUser.getUsername());
//        List <Hobby> hobby = hobbyDao.findAll();
//        model.addAttribute("hobbies", hobby);
//
//        return "users/profile";
//    }


}