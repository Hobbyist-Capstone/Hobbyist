package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HobbyController {

    private HobbyRepository hobbyDao;
    private UserHobbyRepository userHobbyDao;

    public HobbyController(HobbyRepository hobbyDao, UserHobbyRepository userHobbyDao) {
        this.hobbyDao = hobbyDao;
        this.userHobbyDao = userHobbyDao;
    }

//    @GetMapping("/hobby")
//    public String index(Model model) {
//        model.addAttribute("hobby", hobbyDao.findAll());
//        return "hobby/hobbyView";
//    }

    @GetMapping("/hobby/{id}")
    public String singleHobby(@PathVariable long id, Model model) {
        model.addAttribute("hobby", hobbyDao.getOne(id));
        return "hobby/hobbyView";
    }

    @GetMapping("/profile/{id}/status")
    public String showHobbyStatusPage(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUser.getHobbies());
        System.out.println(userHobbyDao.findByUserId(id).getStatus());





        return "users/hobbyStatus";
    }


}