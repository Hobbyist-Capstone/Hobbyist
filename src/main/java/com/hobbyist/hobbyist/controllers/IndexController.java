package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index(Model  model){
        return "index";
    }




}
