package com.hobbyist.hobbyist.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutUsController {

    @GetMapping("/about-us")
    public String profile(Model model) {
        return "about/about-us";
    }
}