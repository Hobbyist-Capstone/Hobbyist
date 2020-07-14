package com.hobbyist.hobbyist.controllers;
import com.hobbyist.hobbyist.repos.CategoryRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hobbyist.hobbyist.repos.HobbyRepository;

@Controller
public class ProfileController {

    @GetMapping("/profile-view")
    public String profile(Model model) {
        return "users/profile-view";
    }
}