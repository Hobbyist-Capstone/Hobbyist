package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HobbyController {

    private HobbyRepository hobbyDao;

    public HobbyController(HobbyRepository hobbyDao) {
        this.hobbyDao = hobbyDao;
    }

//     displays a single hobby by {id}
    @GetMapping("/hobby/{id}")
    public String singleHobby(@PathVariable long id, Model model) {
        model.addAttribute("hobby", hobbyDao.getOne(id));
        return "hobby/hobbyView";
    }

//     displays all hobbies
    @GetMapping("/hobbies")
    public String allHobbies(Model model) {
        model.addAttribute("hobbies", hobbyDao.findAll());
        return "hobbies/allHobbiesView";
    }
}