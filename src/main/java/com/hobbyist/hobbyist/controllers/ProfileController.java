package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import com.hobbyist.hobbyist.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProfileController {

//    @GetMapping("/profile-view")
//    public String profile(Model model) {
//        return "users/profile-view";
//    }

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private UserHobbyRepository userHobbyDao;
    private HobbyRepository hobbyDao;


    private UserService userService;

    public ProfileController(UserRepository userDao, PasswordEncoder passwordEncoder, UserHobbyRepository userHobbyDao, HobbyRepository hobbyDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userHobbyDao = userHobbyDao;
        this.hobbyDao = hobbyDao;
    }

    //user logged in profile
    @GetMapping("/profile")
    public String showProfile(Model vModel) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        List<UserHobby> userHobby = userHobbyDao.findAllByUserId(currentUser.getId());
        vModel.addAttribute("userHobbyList", userHobby);
        vModel.addAttribute("user", userDao.findByUsername(currentUser.getUsername()));
        vModel.addAttribute("friendsList", userInDb.getFriends());
        return "users/profile-view";
    }



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
        return "users/edit";
    }


    @PostMapping("users/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User userToEdit) {
        userToEdit.setId(id);
        userToEdit.setPassword(passwordEncoder.encode(userToEdit.getPassword()));
        userDao.save(userToEdit);
        return "redirect:/profile/" + userToEdit.getUsername();
    }

    //public profile - this is the most accurate friendslist for the user that is not "you"
    @GetMapping("/users/profile/{username}")
    public String showPublicUsersProfile(@PathVariable String username, Model vModel) {
        User user = userDao.findByUsername(username);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        List<UserHobby> userHobby = userHobbyDao.findAllByUserId(user.getId());
        vModel.addAttribute("userHobbyList", userHobby);
        vModel.addAttribute("friendsList", user.getFriends());
        vModel.addAttribute("user", userDao.findByUsername(username));
        vModel.addAttribute("user", user);
        vModel.addAttribute("userName", currentUser.getUsername());
        return "users/profile-view";
    }
}