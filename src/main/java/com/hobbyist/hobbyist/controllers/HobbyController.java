package com.hobbyist.hobbyist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HobbyController {

//    private HobbyRepository hobbyDao;
//
//    public HobbyController(HobbyRepository hobbyDao) {
//        this.hobbyDao = hobbyDao;
//    }

    @GetMapping("/hobby")
    @ResponseBody
    public String index() {
        return "Showing hobby";
    }
}