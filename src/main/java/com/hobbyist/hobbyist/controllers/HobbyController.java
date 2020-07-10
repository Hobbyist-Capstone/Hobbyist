package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HobbyController {

    private HobbyRepository hobbyDao;

    public HobbyController(HobbyRepository hobbyDao) {
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("/hobby")
    public String index(Model model) {
        model.addAttribute("hobby", hobbyDao.findAll());
        return "hobby/hobbyView";
    }
}